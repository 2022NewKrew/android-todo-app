package com.survivalcoding.todolist.presentation.todolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.survivalcoding.todolist.databinding.FragmentToDoListBinding
import com.survivalcoding.todolist.presentation.main.MainActivity
import com.survivalcoding.todolist.presentation.main.MainViewModel
import com.survivalcoding.todolist.presentation.main.ToDoListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ToDoListFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    private var binding: FragmentToDoListBinding? = null

    private val adapter: ToDoListAdapter by lazy {
        ToDoListAdapter(
            onItemCheckedChanged = viewModel::changeDoneState,
            onDeleteButtonClick = viewModel::deleteToDo,
            onItemClick = {
                (activity as MainActivity).navigateToCreateToDo(it)
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentToDoListBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.toDoListRecyclerView?.adapter = adapter
        binding?.createButton?.setOnClickListener {
            if (activity is MainActivity) {
                (activity as MainActivity).navigateToCreateToDo()
            }
        }

        collect()
    }

    private fun collect() {
        repeatOnStart { viewModel.toDoList.collect { adapter.submitList(it) } }
    }

    private fun repeatOnStart(block: suspend CoroutineScope.() -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED, block)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = ToDoListFragment().apply {
            arguments = Bundle().apply {}
        }
    }
}