package com.example.todolist.data.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(var content: String, var isDone: Boolean) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}
