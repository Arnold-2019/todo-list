package com.example.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.room.Todo

class TodoItemAdapter(private val todoList: List<Todo>) : RecyclerView.Adapter<TodoItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_item, parent, false)
        return TodoItemViewHolder(view)
    }

    override fun getItemCount() = todoList.size

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        val todo = todoList[position]
        holder.todoItemCheckBox.text = todo.content
        holder.todoItemCheckBox.isChecked = todo.isDone
    }

}
