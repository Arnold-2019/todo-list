package com.example.todolist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.logic.Repository
import com.example.todolist.logic.dao.Todo

class TodoListViewModel : ViewModel() {

    private val todoList: LiveData<MutableList<Todo>> = Repository.searchAllItems()

    fun addTodoItem(item: Todo) {

        Repository.add(item)

    }

    fun deleteAllTodoItems() {

        Repository.deleteAllItems()

    }

    fun searchAllTodoItems(): LiveData<MutableList<Todo>> {

        return todoList

    }
}
