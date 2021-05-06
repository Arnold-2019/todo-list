package com.example.todolist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.logic.dao.Todo
import com.example.todolist.ui.TodoListAdapter
import com.example.todolist.ui.TodoListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: TodoListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(TodoListViewModel::class.java)

        val adapter = TodoListAdapter()

        recyclerView.run {
            layoutManager = LinearLayoutManager(this@MainActivity)
            this.adapter = adapter
        }

        viewModel.searchAllTodoItems().observe(this, Observer {

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
}
