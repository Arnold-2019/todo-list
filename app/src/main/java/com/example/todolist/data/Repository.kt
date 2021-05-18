package com.example.todolist.data

import com.example.todolist.TodoListApplication
import com.example.todolist.data.dao.AppDatabase
import com.example.todolist.data.dao.Todo
import com.example.todolist.data.dao.TodoDao
import kotlin.concurrent.thread

object Repository {

    private val todoDao: TodoDao = AppDatabase.getDatabase(TodoListApplication.context).todoDao()

    fun add(item: Todo, callBack: (todoList: MutableList<Todo>) -> Unit) {
        thread {
            item.id = todoDao.insertTodo(item)
            callBack(todoDao.loadAllTodoItems())
        }
    }

    fun searchAllItems(callBack: (todoList: MutableList<Todo>) -> Unit) {
        thread {
            callBack(todoDao.loadAllTodoItems())
        }
    }

    fun deleteAllItems(callBack: (todoList: MutableList<Todo>) -> Unit) {
        thread {
            todoDao.deleteAllTodoItems()
            callBack(todoDao.loadAllTodoItems())
        }
    }

    fun updateItem(item: Todo, callBack: (todoList: MutableList<Todo>) -> Unit) {
        thread {
            todoDao.updateTodo(item)
            callBack(todoDao.loadAllTodoItems())
        }
    }

    fun deleteItem(item: Todo, callBack: (todoList: MutableList<Todo>) -> Unit) {
        thread {
            todoDao.deleteTodoItemById(item.id)
            callBack(todoDao.loadAllTodoItems())
        }
    }
}
