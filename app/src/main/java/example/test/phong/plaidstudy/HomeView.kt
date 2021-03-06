package example.test.phong.plaidstudy

/**
 * Created by user on 7/25/2017.
 */
interface HomeView : MvpView {
    fun showLoading()
    fun showContent()
    fun showError()
    fun showLoadingMore(showing: Boolean)
    fun showLoadingMoreError(t: Throwable)
    fun addOlderItems(items: List<PlaidItem>)
}