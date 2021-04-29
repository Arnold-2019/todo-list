package com.example.todolist.room

import androidx.lifecycle.ViewModel

class TodoListViewModel : ViewModel() {

    val todoList: MutableList<Todo> = mutableListOf()

}
