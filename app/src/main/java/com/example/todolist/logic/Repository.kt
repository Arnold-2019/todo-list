package com.example.todolist.logic

import com.example.todolist.TodoListApplication
import com.example.todolist.logic.dao.AppDatabase
import com.example.todolist.logic.dao.Todo
import kotlin.concurrent.thread

object Repository {

    private val todoDao = AppDatabase.getDatabase(TodoListApplication.context).todoDao()

    fun add(item: Todo) {

        thread { item.id = todoDao.insertTodo(item) }

    }

    fun searchAllItems(): List<Todo> {

        var result: List<Todo> = listOf()

        thread { result = todoDao.loadAllTodoItems() }

        return result
    }

    fun deleteAllItems() {

        thread { todoDao.deleteAllTodoItems() }

    }
}
