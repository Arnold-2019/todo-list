package com.example.todolist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.R
import com.example.todolist.data.dao.Todo
import kotlinx.android.synthetic.main.fragment_edit_todo.view.*


// todo: using shared view model
class EditTodoFragment(private val todo: Todo) : DialogFragment() {
    private lateinit var viewModel: TodoListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_edit_todo, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(TodoListViewModel::class.java)

        with(rootView) {
            dialogEditText.setText(todo.content)

            cancelButton.setOnClickListener {
                dismiss()
            }

            submitButton.setOnClickListener {
                todo.content = dialogEditText.text.toString()
                viewModel.updateItem(todo)
                dismiss()
            }
        }

        return rootView
    }
}
