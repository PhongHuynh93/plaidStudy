package example.test.phong.plaidstudy

import java.util.*
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by user on 7/25/2017.
 */
class RouteCaller<T>(private val startPage: Int = 0,
                     private val itemsPerPage: Int,
                     private val backendMethodToCall: (Int, Int) -> Observable<T>) {

    // info - keep tract the current page
    /**
     * Offset for loading more
     * we will increment this one when we start loading more items (or in other words: load an older page).
     */
    private val olderPageOffset = AtomicInteger(startPage)

    /**
     * A queue that is used to retry "older"
     * pages if they have failed before continue with even more older
     */
    private val olderFailedButRetryLater: Queue<Int> = LinkedBlockingQueue<Int>()

    /**
     * Get an observable to load older data from backend.
     */
    fun getOlderWithRetry(): Observable<T> {

        val pageOffset = if (
        olderFailedButRetryLater.isEmpty()) {
            olderPageOffset.addAndGet(itemsPerPage)
        } else {
            olderFailedButRetryLater.poll()
        }

//        info - use doONError to retry to load the failed page when loading the next page.
        return backendMethodToCall(pageOffset, itemsPerPage)
                .doOnError {
                    olderFailedButRetryLater.add(pageOffset)
                }
    }

    /**
     * Get an observable to load the newest data from backend.
     * This method should be invoked on pull to refresh
     */
    fun getFirst(): Observable<T> {
        return backendMethodToCall(startPage, itemsPerPage)
    }
}