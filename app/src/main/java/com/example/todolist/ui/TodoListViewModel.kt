package com.example.todolist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.data.Repository
import com.example.todolist.data.dao.Todo

class TodoListViewModel : ViewModel() {

    private val _todoList: MutableLiveData<List<Todo>> = MutableLiveData(mutableListOf())
    val todoList: LiveData<List<Todo>> = _todoList

    fun addTodoItem(item: Todo) {
        Repository.add(item) {
            _todoList.postValue(it)
        }
    }

    fun deleteAllTodoItems() {
        Repository.deleteAllItems {
            _todoList.postValue(it)
        }
    }

    fun getAllTodoItems() {
        Repository.searchAllItems {
            _todoList.postValue(it)
        }
    }

    fun updateItem(item: Todo) {
        Repository.updateItem(item) {
            _todoList.postValue(it)
        }
    }

    fun deleteItem(item: Todo) {
        Repository.deleteItem(item) {
            _todoList.postValue(it)
        }
    }
}
