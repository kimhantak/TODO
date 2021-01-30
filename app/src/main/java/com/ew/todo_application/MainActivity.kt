package com.ew.todo_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import adapter.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import java.text.DateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    lateinit var realtime: TextView

    lateinit var list: RecyclerView
    lateinit var adapter: ListAdapter

    lateinit var add: Button
    lateinit var delete: Button

    lateinit var time: CoroutineScope

    private val format: DateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        realtime = findViewById(R.id.realtime)
        add = findViewById(R.id.add)
        list = findViewById(R.id.list)
        delete = findViewById(R.id.delete)

        adapter = ListAdapter(this)

        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(this)
        list.setHasFixedSize(true)

        time = CoroutineScope(Dispatchers.Default)
        time.launch {
            while (true) {
                tick()
                delay(100L)
            }
        }
    }

    private suspend fun tick() {
        realtime.text =
                LocalDateTime
                        .now()
                        .format(format)
    }

    override fun onDestroy() {
        super.onDestroy()
        time.cancel()
    }
}