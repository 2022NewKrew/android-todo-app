package com.survivalcoding.todolist.presentation.main.createtodo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.survivalcoding.todolist.databinding.FragmentCreateToDoBinding
import com.survivalcoding.todolist.domain.model.ToDo
import com.survivalcoding.todolist.presentation.main.MainActivity
import com.survivalcoding.todolist.presentation.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CreateToDoFragment : Fragment() {

    private val viewModel: CreateToDoViewModel by viewModels()
    private val activityViewModel: MainViewModel by activityViewModels()

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
        }
        binding?.newToDoEditText?.setText(viewModel.prevToDo?.title)
        binding?.newToDoEditText?.let {
            if (activity is MainActivity) {
                (activity as MainActivity).showKeyboard(it)
            }
        }

        collect()
    }

    private fun collect() {
        repeatOnStart {
            viewModel.newToDoCreatedEvent.collect {
                if (it.editedFlag) {
                    activityViewModel.updateToDo(it.toDo.id, it.toDo)
                } else {
                    activityViewModel.addToDo(it.toDo)
                }

                parentFragmentManager.popBackStack()
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

    companion object {
        @JvmStatic
        fun newInstance(toDo: ToDo?) = CreateToDoFragment().apply {
            arguments = Bundle().apply {
                putParcelable(CreateToDoViewModel.TODO, toDo)
            }
        }
    }
}