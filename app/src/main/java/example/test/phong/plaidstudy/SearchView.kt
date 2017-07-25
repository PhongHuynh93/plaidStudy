package example.test.phong.plaidstudy

/**
 * Created by user on 7/25/2017.
 */
interface SearchView : MvpView {
    fun showLoading()
    fun showContent()
    fun showError(t: Throwable)
    fun showLoadingMore(showing: Boolean)
    fun showLoadingMoreError(t: Throwable)
    fun addOlderItems(items: List<PlaidItem>)
    fun showSearchNotStarted()
}