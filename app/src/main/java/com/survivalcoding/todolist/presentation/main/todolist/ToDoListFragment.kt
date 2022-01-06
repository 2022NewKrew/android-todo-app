package com.survivalcoding.todolist.presentation.main.todolist

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.survivalcoding.todolist.R
import com.survivalcoding.todolist.databinding.FragmentToDoListBinding
import com.survivalcoding.todolist.presentation.main.createtodo.CreateToDoViewModel
import com.survivalcoding.todolist.presentation.main.todolist.adapter.ToDoListAdapter
import com.survivalcoding.todolist.presentation.main.todolist.adapter.ToDoListFooterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ToDoListFragment : Fragment() {

    private val viewModel: ToDoListViewModel by viewModels()

    private var binding: FragmentToDoListBinding? = null

    private val toDoListBodyAdapter: ToDoListAdapter by lazy {
        ToDoListAdapter(
            onItemCheckedChanged = viewModel::changeDoneState,
            onDeleteClick = viewModel::deleteToDo,
            onModifyClick = {
                findNavController().navigate(
                    R.id.action_toDoListFragment_to_createToDoFragment, bundleOf(
                        CreateToDoViewModel.TODO to it
                    )
                )
            }
        )
    }
    private val toDoListFooterAdapter: ToDoListFooterAdapter by lazy {
        ToDoListFooterAdapter()
    }
    private val toDoListConcatAdapter: ConcatAdapter by lazy {
        ConcatAdapter(toDoListBodyAdapter, toDoListFooterAdapter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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
        binding?.toDoListRecyclerView?.adapter = toDoListConcatAdapter
        binding?.createButton?.setOnClickListener {
            findNavController().navigate(R.id.action_toDoListFragment_to_createToDoFragment)
        }
        binding?.searchEditText?.doOnTextChanged { text, _, _, _ ->
            viewModel.searchToDo(text)
        }

        collect()
    }

    private fun collect() {
        repeatOnStart {
            viewModel.toDoList
                .collectLatest { toDoListFlow ->
                    toDoListFlow.collectLatest { toDoList ->
                        toDoListBodyAdapter.submitList(toDoList)
                    }
                }
        }
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.to_do_list_order_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val toDoListOrder = when (item.itemId) {
            R.id.time_asc -> OrderBy.TIME_ASC
            R.id.time_desc -> OrderBy.TIME_DESC
            R.id.title_asc -> OrderBy.TITLE_ASC
            R.id.title_desc -> OrderBy.TITLE_DESC
            else -> OrderBy.TIME_ASC
        }
        viewModel.setOrder(toDoListOrder)

        return true
    }
}