package database

import android.content.Context
import kotlinx.coroutines.*
import model.ItemData
import presenter.TodoContract

class DatabaseController(
        private val context: Context,
        private val view: TodoContract.View
        ) : TodoContract.Controller {

    private fun item2Todo(item: ItemData) : Todo = Todo(item.id, item.text)

    private val scope: CoroutineScope = CoroutineScope(Job() + Dispatchers.IO)

    private suspend fun coroutineFunc() {
        var list = listOf<Todo>()
        scope.launch {
            list = TodoDatabase.getInstance(context).getDao().getAll() as ArrayList<Todo>
        }.join()
        view.addItems(list as ArrayList<Todo>)
    }

    override fun getList() {
        scope.launch {
            coroutineFunc()
        }
    }

    override fun insertItem(item: ItemData) {
        scope.launch {
            TodoDatabase.getInstance(context).getDao().insert(item2Todo(item))
        }
    }

    override fun deleteItem(item: ItemData) {
        scope.launch {
            TodoDatabase.getInstance(context).getDao().delete()
        }
    }
}