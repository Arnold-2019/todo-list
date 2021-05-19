package com.example.todolist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.data.dao.Todo
import com.example.todolist.ui.TodoListAdapter
import com.example.todolist.ui.TodoItemChangedListener
import com.example.todolist.ui.TodoListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TodoItemChangedListener {

    lateinit var viewModel: TodoListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(TodoListViewModel::class.java)

        val adapter = TodoListAdapter(this, this)

        recyclerView.run {
            layoutManager = LinearLayoutManager(this@MainActivity)
            this.adapter = adapter
        }

        viewModel.getAllTodoItems()

        viewModel.todoList.observe(this, Observer {
            adapter.setList(it)
        })

        addButton.setOnClickListener {
            val input = editText.text.toString()
            if (input.isNotBlank()) {
                viewModel.addTodoItem(
                    Todo(input, false)
                )

                editText.text = null
            }
        }

        clear_all.setOnClickListener {
            viewModel.deleteAllTodoItems()
        }
    }

    override fun checkedChanged(todo: Todo) {
        viewModel.updateItem(todo)
    }

    override fun deleteButtonClicked(todo: Todo) {
        viewModel.deleteItem(todo)
    }
}
