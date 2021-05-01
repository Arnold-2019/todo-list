package com.example.todolist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.logic.dao.Todo

class TodoListAdapter(private val todoList: List<Todo>) :
    RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val todoItemCheckBox: CheckBox = view.findViewById(R.id.checkbox_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = todoList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = todoList[position]
        holder.todoItemCheckBox.text = todo.content
        holder.todoItemCheckBox.isChecked = todo.isDone
    }
}
