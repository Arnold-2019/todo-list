package com.example.todolist.data.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoDetails(var content: String, var isDone: Boolean) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}
