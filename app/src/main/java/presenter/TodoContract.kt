package presenter

import database.Todo
import model.ItemData

interface TodoContract {
    interface View {
        fun addItems(todo: ArrayList<Todo>)
        fun notifyDataChanged()
        fun addClicked()
        fun deleteClicked()
        fun getItem() : ItemData
        fun clearList()
    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
        fun setController(controller: Controller)
        fun loadList()
        fun hotReload()
        fun insert()
        fun delete()
    }

    interface Controller {
        fun getList()
        fun insertItem(item: ItemData)
        fun deleteItem(item: ItemData)
    }
}