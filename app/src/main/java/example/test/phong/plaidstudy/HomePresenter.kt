package example.test.phong.plaidstudy

/**
 * Created by user on 7/25/2017.
 */
class HomePresenter(private val itemLoader: ItemsLoader<List<PlaidItem>>) : RxPresenter<HomeView, List<PlaidItem>>() {
    fun loadItems() {
        view?.showLoading()
        subscribe(
                itemsLoader.firstPage(),
                { // onError
                    view?.showError()
                },
                { // onNext
                    view?.addOlderItems(it)
                    view?.showContent()
                }
        )
    }

    fun loadMore() {

        view?.showLoadingMore(true)
        subscribe(
                itemsLoader.olderPages(),
                { // onError
                    view?.showLoadingMoreError(it)
                },
                { // onNext
                    view.addOlderItems(it)
                    view.showLoadingMore(false)
                }
        )
    }
}