package com.example.todolist

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.room.AppDatabase
import com.example.todolist.room.Todo
import com.example.todolist.room.TodoDao
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private var todoList : List<Todo>? = null

    lateinit var editor: SharedPreferences.Editor

    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initInstance()

        val todoDao = AppDatabase.getDatabase(this).todoDao()

        addButton.setOnClickListener {
            val input = editText.text.toString()
            if (input.isNotBlank()) {
//                editor.run {
//                    putString("${++id}", editText.text.toString())
//                    apply()
//                }

                thread {
                    val todoItem = Todo(input, false)
                    todoItem.id = todoDao.insertTodo(todoItem)
                }

                editText.text = null
            }
            prepareTodoList(todoDao)

            recyclerView.run {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = todoList?.let { TodoItemAdapter(it) }
    //            adapter = TodoItemAdapter(todoList = listOf(
    //                    TodoItem("Fetch my id card tomorrow afternoon", false),
    //                    TodoItem("Finish my homework about Android storage", false)
    //            ))
            }
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

    private fun prepareTodoList(todoDao: TodoDao) {

        thread {
            todoList = todoDao.loadAllTodoItems()
        }
    }
}
