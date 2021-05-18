package com.example.todolist.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.data.dao.Todo
import com.example.todolist.ui.TodoListAdapter.TodoListViewHolder

class TodoListAdapter(
    // todo: use interface
    private val viewModel: TodoListViewModel,
    private val context: Context
) : RecyclerView.Adapter<TodoListViewHolder>() {

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

    override fun getItemCount(): Int = if (todoList.isNullOrEmpty()) 0 else todoList.size

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        holder.bind(todoList[position])
    }

    inner class TodoListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val todoItemCheckBox: CheckBox = view.findViewById(R.id.checkbox_item)
        private val deleteButton: Button = view.findViewById(R.id.delete_button)
        private val editButton: Button = view.findViewById(R.id.edit_button)

        fun bind(todo: Todo) {
            with(todoItemCheckBox) {
                text = todo.content
                isChecked = todo.isDone
                setOnCheckedChangeListener { _: CompoundButton, isChecked: Boolean ->
                    todo.isDone = isChecked
                    viewModel.updateItem(todo)
                }
            }

            with(deleteButton) {
                isVisible = todoItemCheckBox.isChecked
                setOnClickListener {
                    if (todoItemCheckBox.isChecked)
                        viewModel.deleteItem(todo)
                }
            }

            editButton.setOnClickListener {
                val dialog = EditTodoFragment(todo)
                dialog.show((context as AppCompatActivity).supportFragmentManager, "editTodoDialog")
            }
        }
    }
}
