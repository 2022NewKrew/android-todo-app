package com.survivalcoding.todolist.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.todolist.App
import com.survivalcoding.todolist.databinding.FragmentMainBinding
import com.survivalcoding.todolist.domain.entity.Task
import com.survivalcoding.todolist.ui.MainActivity
import com.survivalcoding.todolist.ui.MainViewModel
import com.survivalcoding.todolist.ui.MainViewModelFactory
import com.survivalcoding.todolist.ui.add.AddEditFragment
import com.survivalcoding.todolist.ui.main.adapter.OnClickEvent
import com.survivalcoding.todolist.ui.main.adapter.ToDoListAdapter
import java.util.*

class MainFragment : Fragment() {
    companion object {
        const val ADD_MODE = true
        const val EDIT_MODE = false
    }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<MainViewModel> {
        MainViewModelFactory((requireActivity().application as App).taskRepository)
    }

    private var clickTime = -1L
    private var toast: Toast? = null

    private val clickEvent = object : OnClickEvent {
        override fun isDoneClickEvent(task: Task) {
            val newTask = task.copy(isDone = !task.isDone)
            viewModel.upsertTask(newTask)
        }

        override fun longClickEvent(task: Task): Boolean {
            viewModel.setMode(EDIT_MODE)
            viewModel.setTask(task)

            (requireActivity() as MainActivity).replaceFragment(AddEditFragment())
            return true
        }

        override fun expandClickEvent(task: Task) {
            val newTask = task.copy(isExpanded = !task.isExpanded)
            viewModel.upsertTask(newTask)
        }
    }

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

        val adapter by lazy {
            ToDoListAdapter(clickEvent).apply {
                stateRestorationPolicy =
                    RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            }
        }
        viewModel.getTasksLive().observe(this) { adapter.submitList(it) }

        setTitleTime()
        makeBackPressedCallback()

        binding.tvAddTask.setOnClickListener {
            viewModel.setMode(ADD_MODE)
            viewModel.setTask(Task())
            (requireActivity() as MainActivity).replaceFragment(AddEditFragment())
        }
        binding.rvTaskList.adapter = adapter
        binding.etSearch.doOnTextChanged { text, _, _, _ ->
            if (binding.etSearch.isFocusable && text.toString() != "") {
                val tasks = searchResultTasks(text.toString())
                adapter.submitList(tasks)
            } else {
                adapter.submitList(viewModel.getTasksList())
            }
        }
    }

    private fun searchResultTasks(text: String): List<Task>? {
        val tasks = viewModel.getTasksList().filter {
            it.taskName.lowercase().replace(" ", "").contains(text.lowercase())
        }
        return tasks
    }

    private fun makeBackPressedCallback() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            with(requireActivity()) {
                val curClickTime = Date().time
                when {
                    clickTime != -1L && curClickTime - clickTime < 1200 -> {
                        toast?.cancel()
                        finish()
                    }
                    else -> {
                        clickTime = curClickTime
                        toast = Toast.makeText(this, "종료하려면 한번 더 눌러주세요.", Toast.LENGTH_SHORT)
                        toast?.show()
                    }
                }
            }
        }
    }

    private fun setTitleTime() {
        val today = Calendar.getInstance()
        binding.tvTopTimeYear.text = today.get(Calendar.YEAR).toString()
        binding.tvTopTimeMonth.text = when (today.get(Calendar.MONTH)) {
            Calendar.JANUARY -> "JAN"
            Calendar.FEBRUARY -> "FEB"
            Calendar.MARCH -> "MAR"
            Calendar.APRIL -> "APR"
            Calendar.MAY -> "MAY"
            Calendar.JUNE -> "JUN"
            Calendar.JULY -> "JUL"
            Calendar.AUGUST -> "AUG"
            Calendar.SEPTEMBER -> "SEP"
            Calendar.OCTOBER -> "OCT"
            Calendar.NOVEMBER -> "NOV"
            else -> "DEC"
        }
        binding.tvTopTimeDate.text = today.get(Calendar.DATE).toString()
        binding.tvTopTimeDay.text = when (today.get(Calendar.DAY_OF_WEEK)) {
            Calendar.MONDAY -> "MONDAY"
            Calendar.TUESDAY -> "TUESDAY"
            Calendar.WEDNESDAY -> "WEDNESDAY"
            Calendar.THURSDAY -> "THURSDAY"
            Calendar.FRIDAY -> "FRIDAY"
            Calendar.SATURDAY -> "SATURDAY"
            else -> "SUNDAY"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}