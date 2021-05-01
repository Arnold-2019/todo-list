package com.example.todolist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.room.AppDatabase
import com.example.todolist.room.Todo
import com.example.todolist.room.TodoDao
import com.example.todolist.room.TodoListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var todoDao: TodoDao

    lateinit var viewModel: TodoListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoDao = AppDatabase.getDatabase(this).todoDao()

        viewModel = ViewModelProvider(this).get(TodoListViewModel::class.java)
        viewModel.initTodoList(todoDao)

        recyclerView.layoutManager = LinearLayoutManager(this)

        addButton.setOnClickListener {
            val input = editText.text.toString()

            if (input.isNotBlank()) {
                val todoItem = Todo(input, false)
                thread { todoItem.id = todoDao.insertTodo(todoItem) }
                viewModel.addTodoItem(todoItem)
            }
            refresh()
        }

        clear_all.setOnClickListener {
            thread {
                todoDao.deleteAllTodoItems()
            }
            viewModel.initTodoList(todoDao)
            refresh()
        }

        refresh()
    }

    override fun onResume() {
        super.onResume()
        refresh()
    }

    private fun refresh() {
        recyclerView.adapter = TodoItemAdapter(viewModel.todoList)
        editText.text = null
    }
}
