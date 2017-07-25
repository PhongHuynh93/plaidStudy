package example.test.phong.plaidstudy

import io.reactivex.Observable

/**
 * Created by user on 7/25/2017.
 */
class DesignerNewsSearchCallerFactory(private val searchQuery: String, private val backend: DesignerNewsService) : RouteCallerFactory<List<PlaidItem>> {

    val extractPlaidItemsFromStory = fun(story: StoriesResponse): List<PlaidItem> {
        return story.stories
    }

    // The method to execute from RouteCaller
    val searchCall = fun(pageOffset: Int, itemsPerPage: Int): Observable<List<PlaidItem>> {
        return backend.search(searchQuery, pageOffset).map(extractPlaidItemsFromStory)
    }

    // Create a list with one single RouteCaller() with "searchCall" as method reference
    private val callers = arrayListOf(RouteCaller(0, 100, searchCall))

    override fun getAllBackendCallers(): Observable<List<RouteCaller<List<PlaidItem>>>> {
        return Observable.just(callers)
    }
}