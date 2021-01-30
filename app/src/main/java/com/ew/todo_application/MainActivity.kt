package com.ew.todo_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    lateinit var realtime: TextView

    lateinit var list: RecyclerView

    lateinit var add: Button
    lateinit var delete: Button

    lateinit var time: CoroutineScope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        realtime = findViewById(R.id.realtime)
        add = findViewById(R.id.add)
        list = findViewById(R.id.list)
        delete = findViewById(R.id.delete)

        time = CoroutineScope(Dispatchers.Default)
        time.launch {
            while (true) {
                tick()
                delay(100L)
            }
        }
    }

    private suspend fun tick() {
        realtime.text = LocalDateTime
                            .now()
                            .format(DateTimeFormatter.ISO_TIME)
    }

    override fun onDestroy() {
        super.onDestroy()
        time.cancel()
    }
}