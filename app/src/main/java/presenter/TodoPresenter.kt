package presenter

class TodoPresenter : TodoContract.Presenter {

    private var view: TodoContract.View? = null
    private var controller: TodoContract.Controller? = null

    override fun attachView(view: TodoContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun setController(controller: TodoContract.Controller) {
        this.controller = controller
    }

    override fun insert() {
        view?.getItem()?.let { controller?.insertItem(it) }
    }

    override fun delete() {
        view?.getItem()?.let { controller?.deleteItem(it) }
    }

    override fun loadList() {
        view?.clearList()
        controller?.getList()
        view?.notifyDataChanged()
    }

    override fun hotReload() {
        loadList()
    }
}