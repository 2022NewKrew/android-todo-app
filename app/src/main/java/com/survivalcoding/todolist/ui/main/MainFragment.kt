package com.survivalcoding.todolist.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.survivalcoding.todolist.R
import com.survivalcoding.todolist.databinding.FragmentMainBinding
import com.survivalcoding.todolist.data.model.Todo
import com.survivalcoding.todolist.ui.MainViewModel
import com.survivalcoding.todolist.ui.main.adapter.TodoListAdapter
import com.survivalcoding.todolist.ui.todo.TodoFragment

class MainFragment: Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = TodoListAdapter { id ->
            viewModel.todos.value?.find { it.id == id }?.let { selectedTodo ->
                launchTodoFragment(selectedTodo)
            }
        }

        binding.todoListRv.adapter = adapter
        viewModel.todos.observe(this) { adapter.submitList(it) }

        binding.fab.setOnClickListener {
            launchTodoFragment(null)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchTodoFragment(todo: Todo?) {
        viewModel.selectedTodo = todo
        requireActivity().supportFragmentManager.commit {
            replace<TodoFragment>(R.id.mainFcv)
            setReorderingAllowed(false)
            addToBackStack(null)
        }
    }
}