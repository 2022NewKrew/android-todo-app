package com.survivalcoding.todolist.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.survivalcoding.todolist.R
import com.survivalcoding.todolist.databinding.FragmentMainBinding
import com.survivalcoding.todolist.presentation.MainViewModel
import com.survivalcoding.todolist.presentation.main.adapter.TodoListAdapter
import com.survivalcoding.todolist.presentation.upsert.UpsertFragment

class MainFragment : Fragment() {
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
        super.onViewCreated(view, savedInstanceState)

        val adapter = TodoListAdapter(onClickCheckBox = { modify ->
            viewModel.toggleTodo(modify)
        }, onClickViewShort = { pos ->
            moveToAddFragment(pos)
        }, onClickViewLong = { delete ->
            viewModel.deleteTodo(delete)
        })

        val recyclerView = binding.todoRecyclerView

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //UI를 변경하는 부분을 관찰할 수 있게 확인
        viewModel.todos.observe(this) { todos ->
            adapter.submitList(todos)
        }

        //Add Button을 통해 다른 액티비티로 이동
        val addButton = binding.addButton
        addButton.setOnClickListener {
            moveToAddFragment()
        }
    }

    private fun moveToAddFragment(pos: Int = NEW) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragment_container_view,
                UpsertFragment().apply {
                    if (pos != NEW) this.arguments =
                        bundleOf(MODIFY to viewModel.getTodoByIndex(pos))
                })
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val NEW = -1
        const val MODIFY = "modify"
    }
}