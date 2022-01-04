package com.survivalcoding.todolist.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.survivalcoding.todolist.databinding.FragmentMainBinding
import com.survivalcoding.todolist.domain.models.TodoItem
import com.survivalcoding.todolist.ui.write.SimpleTodoWriteActivity

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val NEW_ID = "newID"
    }

    private val viewModel by activityViewModels<MainActivityViewModel>()

    private val getResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                it.data?.getParcelableExtra<TodoItem>(SimpleTodoWriteActivity.NEW_TODO)
                    ?.let { content -> viewModel.addTodo(content) }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TodoAdapter(viewModel::toggleTodoDone)

        binding.recyclerViewTodoList.adapter = adapter

        viewModel.data.observe(this) { todos ->
            adapter.submitList(todos)
        }

        binding.fab.setOnClickListener {
            val intent = Intent(this.context, SimpleTodoWriteActivity::class.java)
            intent.putExtra(NEW_ID, viewModel.data.value?.size)
            getResult.launch(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}