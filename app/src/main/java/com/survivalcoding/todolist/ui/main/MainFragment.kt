package com.survivalcoding.todolist.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.survivalcoding.todolist.R
import com.survivalcoding.todolist.data.TodoRepositoryImpl
import com.survivalcoding.todolist.data.TodoRoomDataBase
import com.survivalcoding.todolist.databinding.FragmentMainBinding
import com.survivalcoding.todolist.ui.main.adapter.TodoListAdapter
import com.survivalcoding.todolist.ui.main.adapter.TodoSwipeHandler


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel by activityViewModels<MainViewModel> {
        MainViewModelFactory(
            // TodoInMemoryRepositoryImpl()
            TodoRepositoryImpl(
                Room.databaseBuilder(
                    requireContext(),
                    TodoRoomDataBase::class.java, TodoRoomDataBase.DATABASE_NAME
                ).allowMainThreadQueries().build()
            )
        )
    }

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
            },
            onLeftSwiped = { todo ->
                mainViewModel.removeTodo(todo)
            }
        )

        val callback: ItemTouchHelper.Callback = TodoSwipeHandler(todoListAdapter)
        val helper = ItemTouchHelper(callback)
        helper.attachToRecyclerView(binding.recyclerview)

        // enroll listAdapter
        binding.recyclerview.adapter = todoListAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())

        //observe
        mainViewModel.todos.observe(this, { todos ->
            todoListAdapter.submitList(todos.sortedByDescending { it.timestamp }
                .sortedBy { it.isDone })
        })
        // menu -> button
        binding.addBtn.setOnClickListener {
            parentFragmentManager.commit {
                replace<EditFragment>(R.id.fragment_container_view)
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredList = mainViewModel.searchTodos(newText)
                todoListAdapter.submitList(filteredList)
                return false
            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
