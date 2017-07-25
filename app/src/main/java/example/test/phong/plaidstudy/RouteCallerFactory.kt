package example.test.phong.plaidstudy

import io.reactivex.Observable

/**
 * Created by user on 7/25/2017.
 */
interface RouteCallerFactory<T> {

    /**
     * Get all available backend route callers
     */
    fun getAllBackendCallers(): Observable<List<RouteCaller<T>>>
}