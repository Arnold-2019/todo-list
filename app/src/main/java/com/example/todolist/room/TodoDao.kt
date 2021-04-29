package com.example.todolist.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDao {

    @Insert
    fun insertTodo(todo: Todo): Long

    @Update
    fun updateTodo(newTodo: Todo)

    @Query("select * from Todo")
    fun loadAllTodoItems(): MutableList<Todo>

    @Query("select * from Todo where isDone is 1")
    fun loadTodoItems(): List<Todo>

    @Delete
    fun deleteTodoItem(todo: Todo)

    @Query("delete from Todo where id = :id")
    fun deleteTodoItemById(id: Int): Int

    @Query("delete from Todo")
    fun deleteAllTodoItems()

}
