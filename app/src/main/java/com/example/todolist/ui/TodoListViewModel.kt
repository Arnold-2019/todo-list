package com.example.todolist.ui

import androidx.lifecycle.ViewModel
import com.example.todolist.logic.dao.Todo
import com.example.todolist.logic.dao.TodoDao
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
