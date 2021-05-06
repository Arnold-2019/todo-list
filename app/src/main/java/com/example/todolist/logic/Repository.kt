package com.example.todolist.logic

import androidx.lifecycle.LiveData
import com.example.todolist.TodoListApplication
import com.example.todolist.logic.dao.AppDatabase
import com.example.todolist.logic.dao.Todo
import com.example.todolist.logic.dao.TodoDao
import kotlin.concurrent.thread

object Repository {

    private val todoDao: TodoDao = AppDatabase.getDatabase(TodoListApplication.context).todoDao()
    private var todoList: LiveData<MutableList<Todo>>

    init {
        todoList = todoDao.loadAllTodoItems()
    }

    fun add(item: Todo) {

        thread { item.id = todoDao.insertTodo(item) }

    }

    fun searchAllItems(): LiveData<MutableList<Todo>> {

        // async operation, but return directly ??
        // -> call back
        // -> thread communication
        thread { todoList = todoDao.loadAllTodoItems() }
        return todoList

    }

    fun deleteAllItems() {

        thread { todoDao.deleteAllTodoItems() }

    }

    fun updateTodoItem(todo: Todo) {

        thread { todoDao.updateTodo(todo) }

    }
}
