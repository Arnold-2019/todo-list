package com.example.todolist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.data.Repository
import com.example.todolist.data.dao.Todo

class TodoListAdapter(private val viewModel: TodoListViewModel) :
    RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder>() {

    private var todoList: List<Todo> = listOf()

    fun setList(todoList: List<Todo>) {
        this.todoList = todoList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {

        return TodoListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.todo_item, parent, false)
        )
    }

    override fun getItemCount(): Int {

        return if (todoList.isNullOrEmpty()) 0 else todoList.size
    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {

        val todo = todoList[position]

        holder.bind(todo)

        holder.todoItemCheckBox.setOnCheckedChangeListener { _: CompoundButton, isChecked: Boolean ->
            todo.isDone = isChecked
            Repository.updateTodoList(todo) {
                viewModel.updateItem(todo)
            }
        }
    }

    inner class TodoListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val todoItemCheckBox: CheckBox = view.findViewById(R.id.checkbox_item)

        fun bind(todo: Todo) {
            with(todoItemCheckBox) {
                text = todo.content
                isChecked = todo.isDone
            }
        }
    }
}
