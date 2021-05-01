package com.example.todolist

import android.app.Application
import android.content.Context

class TodoListApplication : Application() {

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}
