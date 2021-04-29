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

    private var todoList : List<Todo>? = null

    lateinit var viewModel: TodoListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val todoDao = AppDatabase.getDatabase(this).todoDao()

        recyclerView.layoutManager = LinearLayoutManager(this)

//        addButton.setOnClickListener {
//            val input = editText.text.toString()
//            if (input.isNotBlank()) {
//
//                thread {
//                    val todoItem = Todo(input, false)
//                    todoItem.id = todoDao.insertTodo(todoItem)
//                }
//
//                editText.text = null
//            }
//            prepareTodoList(todoDao)
//
//            recyclerView.run {
//                layoutManager = LinearLayoutManager(this@MainActivity)
//                adapter = todoList?.let { TodoItemAdapter(it) }
//            }
//        }

//        clear_all.setOnClickListener {
//            thread {
//                todoDao.deleteAllTodoItems()
//            }
//        }
        viewModel = ViewModelProvider(this).get(TodoListViewModel::class.java)

        addButton.setOnClickListener {
            viewModel.todoList.add(
                Todo(editText.text.toString(), false)
            )
            refreshRecyclerView()
        }
        refreshRecyclerView()
    }

    private fun refreshRecyclerView() {
        recyclerView.adapter = TodoItemAdapter(viewModel.todoList)
    }

    private fun prepareTodoList(todoDao: TodoDao) {
        thread {
            todoList = todoDao.loadAllTodoItems()
        }
    }
}
