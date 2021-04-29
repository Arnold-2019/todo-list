package com.example.todolist.room

import androidx.lifecycle.ViewModel
import kotlin.concurrent.thread

class TodoListViewModel : ViewModel() {

    var todoList: MutableList<Todo> = mutableListOf()

    fun initTodoList(todoDao: TodoDao) {
        thread {
            todoList = todoDao.loadAllTodoItems()
        }
    }

    fun addTodoItem(item: Todo) {
        todoList.add(item)
    }

}
