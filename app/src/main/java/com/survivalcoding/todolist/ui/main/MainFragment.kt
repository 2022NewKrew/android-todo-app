package com.survivalcoding.todolist.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.recyclerview.widget.LinearLayoutManager
import com.survivalcoding.todolist.R
import com.survivalcoding.todolist.databinding.FragmentMainBinding
import com.survivalcoding.todolist.ui.main.adapter.TodoListAdapter


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val todoListAdapter = TodoListAdapter(
            onItemClicked = { item ->
                mainViewModel.toggleIsDone(item.copy(isDone = !item.isDone))
            },
            onLongClicked = { item ->
                mainViewModel.todoNeedChanged.value = item
                parentFragmentManager.commit {
                    replace<EditFragment>(R.id.fragment_container_view)
                    setReorderingAllowed(true)
                    addToBackStack(null)
                }
            }
        )

        // enroll listAdapter
        binding.recyclerview.adapter = todoListAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())

        mainViewModel.todos.observe(this, { todos ->
            todoListAdapter.submitList(todos)
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_meun, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_todo_menu -> {
                parentFragmentManager.commit {
                    replace<EditFragment>(R.id.fragment_container_view)
                    setReorderingAllowed(true)
                    addToBackStack(null)
                }
                true
            }
            R.id.search_todo_menu -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
