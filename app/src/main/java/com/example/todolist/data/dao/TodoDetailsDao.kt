package com.example.todolist.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDetailsDao {

    @Insert
    fun insertTodo(todo: TodoDetails): Long

    @Update
    fun updateTodo(newTodo: Todo)

    @Query("select * from Todo")
    fun loadAllTodoItems(): MutableList<Todo>

    @Delete
    fun deleteTodoItem(todo: Todo)

    @Query("delete from Todo where id = :id")
    fun deleteTodoItemById(id: Int): Int

    @Query("delete from Todo")
    fun deleteAllTodoItems()
}
