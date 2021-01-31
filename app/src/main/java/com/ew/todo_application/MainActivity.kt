package com.ew.todo_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import adapter.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import database.DatabaseController
import database.Todo
import kotlinx.coroutines.*
import model.ItemData
import presenter.TodoContract
import presenter.TodoPresenter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), TodoContract.View {

    private lateinit var realtime: TextView
    private lateinit var list: RecyclerView
    private lateinit var listAdapter: ListAdapter

    private lateinit var add: Button
    private lateinit var delete: Button

    private lateinit var time: CoroutineScope

    private lateinit var presenter: TodoPresenter

    private val databaseController: DatabaseController by lazy { DatabaseController(this, this) }

    private val customDialog : CustomDialog by lazy { CustomDialog(this) {
            presenter.insert()
            presenter.hotReload()
        }
    }

    private val format: DateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        realtime = findViewById(R.id.realtime)
        add = findViewById(R.id.add)
        list = findViewById(R.id.list)
        delete = findViewById(R.id.delete)

        listAdapter = ListAdapter(this)
        with (list) {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }

        presenter = TodoPresenter().apply {
            attachView(this@MainActivity)
            setController(databaseController)
            loadList()
        }

        customDialog.setting("Alert!", "Enter todo:")

        time = CoroutineScope(Dispatchers.Default)
        time.launch {
            while (true) {
                tick()
                delay(100L)
            }
        }

        add.setOnClickListener { addClicked() }
        delete.setOnClickListener { deleteClicked() }
    }

    private fun tick() {
        realtime.text = LocalDateTime.now().format(format)
    }

    override fun addItems(todo: ArrayList<Todo>) {
        listAdapter.setList(todo)
    }

    override fun notifyDataChanged() {
        this.runOnUiThread {
            listAdapter.notifyDataSetChanged()
        }
    }

    override fun getItem(): ItemData {
        return ItemData(Random().nextInt(Int.MAX_VALUE), customDialog.text)
    }

    override fun addClicked() {
        customDialog.show()
    }

    override fun deleteClicked() {
        presenter.delete()
        presenter.hotReload()
    }

    override fun clearList() {
        listAdapter.clearList()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
        time.cancel()
    }
}