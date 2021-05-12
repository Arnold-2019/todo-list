package com.example.todolist.data

import com.example.todolist.TodoListApplication
import com.example.todolist.data.dao.AppDatabase
import com.example.todolist.data.dao.Todo
import com.example.todolist.data.dao.TodoDao
import kotlin.concurrent.thread

class Repository {

    private val todoDao: TodoDao = AppDatabase.getDatabase(TodoListApplication.context).todoDao()

    fun add(item: Todo) {
        thread { item.id = todoDao.insertTodo(item) }
    }

    fun searchAllItems(callBack: (todoList: MutableList<Todo>) -> Unit) {

        // async operation, but return directly ??
        // -> call back
        // -> thread communication
        thread {
            callBack(todoDao.loadAllTodoItems())
        }
    }

    fun deleteAllItems() {
        thread { todoDao.deleteAllTodoItems() }
    }
}
