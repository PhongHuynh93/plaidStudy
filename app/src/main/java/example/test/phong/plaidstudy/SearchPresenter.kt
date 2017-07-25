package example.test.phong.plaidstudy

/**
 * Created by user on 7/25/2017.
 */
class SearchPresenter(private val itemsLoaderFactory: SearchItemsLoaderFactory) : RxPresenter<SearchView, List<PlaidItem>>() {

    private var itemsLoader: ItemsLoader<List<PlaidItem>>? = null

    fun search(query: String) {

        // Create items loader for the given query string
        itemsLoader = itemsLoaderFactory.create(query)

        view?.showLoading()

        subscribe(itemsLoader!!.firstPage(), { // Error handling
            view?.showError(it)
        }, { // onNext
            view?.addOlderItems(it)
        }, {
            view?.showContent()
        })
    }

    fun searchMore(query: String) {

        view?.showLoadingMore(true)
        subscribe(itemsLoader!!.olderPages(), { // Error handling
            view?.showLoadingMore(false)
            view?.showLoadingMoreError(it)
        }, { // onNext
            view?.addOlderItems(it)
        }, { // onComplete
            view?.showLoadingMore(false)
        })

    }

    fun clearSearch() {
        // Unsubscribe any previous search subscriptions
        unsubscribe()

        view.showSearchNotStarted()
    }
}