package com.example.todolist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.data.Repository
import com.example.todolist.data.dao.Todo

class TodoListViewModel : ViewModel() {

    private val _todoList: MutableLiveData<List<Todo>> = MutableLiveData(mutableListOf())
    var todoList: LiveData<List<Todo>> = _todoList

    private val repo = Repository()

    fun addTodoItem(item: Todo) {
        repo.add(item)
    }

    fun deleteAllTodoItems() {
        repo.deleteAllItems()
    }

    fun getAllTodoItems(){
        repo.searchAllItems{
            _todoList.postValue(it)
        }
    }
}
