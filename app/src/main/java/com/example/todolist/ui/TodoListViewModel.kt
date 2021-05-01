package com.example.todolist.ui

import androidx.lifecycle.ViewModel
import com.example.todolist.logic.Repository
import com.example.todolist.logic.dao.Todo

class TodoListViewModel : ViewModel() {

    val todoList: List<Todo>
        get() = _todoList

    private var _todoList: MutableList<Todo> = mutableListOf()

    init {
        _todoList.addAll(Repository.searchTodoItems())
    }

    fun addTodoItem(item: Todo) {

        Repository.add(item)

        _todoList.add(item)

    }

    fun deleteAllTodoItems() {
        Repository.deleteAllTodoItems()

        _todoList = mutableListOf()
    }
}
