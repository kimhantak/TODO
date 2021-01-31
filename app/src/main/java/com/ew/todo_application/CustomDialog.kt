package com.ew.todo_application

import android.app.AlertDialog
import android.content.Context
import android.view.ViewGroup
import android.widget.EditText

class CustomDialog(context: Context, val block: () -> Unit) : AlertDialog.Builder(context) {

    private val input: EditText = EditText(context)

    var text: String = ""

    fun setting(title: String, content: String) {
        with (this) {
            setTitle(title)
            setMessage(content)
            setView(input)
            setCancelable(false)
            setPositiveButton("ok") { _, _ ->
                text = input.text.toString()
                block() // presenter.insert()
                input.setText("")
                (input.parent as ViewGroup).removeView(input)
            }
            setNegativeButton("no") { _, _ ->
                (input.parent as ViewGroup).removeView(input)
            }
        }
    }
}