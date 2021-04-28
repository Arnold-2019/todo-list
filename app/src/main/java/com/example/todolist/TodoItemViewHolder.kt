package com.example.todolist

import android.view.View
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView

class TodoItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val todoItemCheckBox: CheckBox = view.findViewById(R.id.checkbox_item)

}
