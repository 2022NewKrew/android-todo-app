package com.survivalcoding.todolist.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.survivalcoding.todolist.databinding.FragmentMainBinding
import com.survivalcoding.todolist.presentation.MainViewModel
import com.survivalcoding.todolist.presentation.main.adapter.TodoListAdapter

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

        val adapter = TodoListAdapter(onChangeIsDone = { modify ->
            viewModel.toggleTodo(modify)
        }, onModifyTodo = {
            //Todo: 다른 프래그먼트로 교체 작업
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
            //Todo: 다른 프래그먼트로 교체 작업
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}