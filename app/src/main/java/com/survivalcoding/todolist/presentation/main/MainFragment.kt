package com.survivalcoding.todolist.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.survivalcoding.todolist.App
import com.survivalcoding.todolist.R
import com.survivalcoding.todolist.databinding.FragmentMainBinding
import com.survivalcoding.todolist.presentation.MainViewModel
import com.survivalcoding.todolist.presentation.add.AddEditFragment
import com.survivalcoding.todolist.presentation.main.adapter.TodoListAdapter

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<MainViewModel> {
        MainViewModel.MainViewModelFactory(
            application = requireActivity().application
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // View가 완성된 직후
        val adapter = TodoListAdapter(
            onItemClicked = { todo ->
                parentFragmentManager.commit {
                    replace<AddEditFragment>(
                        R.id.fragment_container_view,
                        args = bundleOf(
                            "todo" to todo
                        )
                    )
                    setReorderingAllowed(true)
                    addToBackStack(null) // name can b
                }
            },
            onItemLongClicked = { todo ->
                viewModel.deleteTodo(todo)
            },
            onCheckClicked = { todo ->
                viewModel.toggleTodo(todo)
            }
        )

        binding.addFab.setOnClickListener {
            parentFragmentManager.commit {
                replace<AddEditFragment>(
                    R.id.fragment_container_view,
                )
                setReorderingAllowed(true)
                addToBackStack(null) // name can b
            }
        }

        binding.todoRecyclerView.adapter = adapter

        viewModel.todos.observe(this) { todos ->
            adapter.submitList(todos)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}