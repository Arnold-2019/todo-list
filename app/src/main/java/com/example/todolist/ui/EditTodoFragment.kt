package com.example.todolist.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.todolist.R
import com.example.todolist.data.dao.Todo
import kotlinx.android.synthetic.main.fragment_edit_todo.view.*


// add view model for this fragment
// or share view model
class EditTodoFragment(private val todo: Todo) : DialogFragment() {
    lateinit var listener: EditTodoDialogListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_edit_todo, container, false)
        with(rootView) {
            dialogEditText.setText(todo.content)

            cancelButton.setOnClickListener {
                dismiss()
            }

            submitButton.setOnClickListener {
                todo.content = dialogEditText.text.toString()
                listener.apply(todo)
                dismiss()
            }
        }

        return rootView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as EditTodoDialogListener
        } catch (e: ClassCastException) {
            throw  ClassCastException("$context must implement EditTodoDialogListener")
        }
    }

    interface EditTodoDialogListener {
        fun apply(todo: Todo)
    }
}
