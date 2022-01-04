package com.survivalcoding.todolist.presentation.todo

import android.os.Bundle
import android.view.*
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.survivalcoding.todolist.R
import com.survivalcoding.todolist.databinding.FragmentTodoBinding
import com.survivalcoding.todolist.model.Todo
import com.survivalcoding.todolist.presentation.MainViewModel
import com.survivalcoding.todolist.toTimestampString

class TodoFragment : Fragment() {

    private var _binding: FragmentTodoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodoBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.selectedTodo?.also {
            binding.apply {
                titleIv.visibility = View.INVISIBLE
                isDoneCb.visibility = View.VISIBLE
                isDoneCb.isChecked = it.isDone
                titleEv.setText(it.title)
                contentEv.setText(it.content)
                timestampIv.visibility = View.VISIBLE
                timestampTv.visibility = View.VISIBLE
                timestampTv.text = it.timestamp.toTimestampString()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            val todo = Todo(
                viewModel.selectedTodo?.id ?: -1,
                binding.titleEv.text.toString(),
                binding.contentEv.text.toString(),
                binding.isDoneCb.isChecked ?: false,
                viewModel.selectedTodo?.let { getTimestamp(it) } ?: System.currentTimeMillis()
            )
            viewModel.upsertTodo(todo)
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_todo, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete -> {
                viewModel.selectedTodo?.let {
                    viewModel.deleteTodo(it)
                }
                requireActivity().supportFragmentManager.popBackStack()
            }
            else -> return false
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun getTimestamp(todo: Todo): Long {
        val conditions = listOf(
            binding.isDoneCb.isChecked == todo.isDone,
            binding.titleEv.text.toString() == todo.title,
            binding.contentEv.text.toString() == todo.content
        )

        return if (conditions.all { it }) {
            todo.timestamp
        } else {
            System.currentTimeMillis()
        }
    }
}