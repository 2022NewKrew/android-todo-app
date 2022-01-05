package com.survivalcoding.todolist.presentation.main.createtodo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.survivalcoding.todolist.R
import com.survivalcoding.todolist.databinding.FragmentCreateToDoBinding
import com.survivalcoding.todolist.presentation.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateToDoFragment : Fragment() {

    private val viewModel: CreateToDoViewModel by viewModels()

    private var binding: FragmentCreateToDoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateToDoBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.saveButton?.setOnClickListener {
            viewModel.createNewToDo(binding?.newToDoEditText?.text.toString())
            findNavController().navigate(R.id.action_createToDoFragment_to_toDoListFragment)
        }
        binding?.newToDoEditText?.setText(viewModel.prevToDo?.title)
        binding?.newToDoEditText?.let {
            if (activity is MainActivity) {
                (activity as MainActivity).showKeyboard(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}