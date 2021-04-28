package com.example.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TodoItemAdapter(val todoList: List<TodoItem>) : RecyclerView.Adapter<TodoItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_item, parent, false)
        return TodoItemViewHolder(view)
    }

    override fun getItemCount() = todoList.size

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        val todoItem = todoList[position]
        holder.todoItemCheckBox.text = todoItem.content
        holder.todoItemCheckBox.isChecked = todoItem.state
    }

}
