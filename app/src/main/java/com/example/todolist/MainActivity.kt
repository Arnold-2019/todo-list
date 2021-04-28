package com.example.todolist

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val todoList : List<TodoItem>? = null

    lateinit var editor: SharedPreferences.Editor

    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initInstance()

        addButton.setOnClickListener {
            val input = editText.text.toString()
            if (input.isNotBlank()) {
                editor.run {
                    putString("${++id}", editText.text.toString())
                    apply()
                }
                editText.text = null
            }
        }

        prepareTodoList()

        recyclerView.run {
            layoutManager = LinearLayoutManager(this@MainActivity)
//            adapter = todoList?.let { TodoItemAdapter(it) }
            adapter = TodoItemAdapter(todoList = listOf(
                    TodoItem("Fetch my id card tomorrow afternoon", false),
                    TodoItem("Finish my homework about Android storage", false)
            ))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        editor.run {
            putInt("id", id)
            apply()
        }
    }

    private fun initInstance() {
        editor = getSharedPreferences("todoList", Context.MODE_PRIVATE).edit()
        id = getSharedPreferences("todoList", Context.MODE_PRIVATE).getInt("id", -1)

        if (id == -1) {
            editor.run {
                putInt("id", 0)
                apply()
            }
            id = 0
        }
    }

    private fun prepareTodoList() {

    }
}
