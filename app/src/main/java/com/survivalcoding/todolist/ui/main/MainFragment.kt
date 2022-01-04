package com.survivalcoding.todolist.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.todolist.databinding.FragmentMainBinding
import com.survivalcoding.todolist.domain.entity.Task
import com.survivalcoding.todolist.ui.MainActivity
import com.survivalcoding.todolist.ui.add.AddEditFragment
import com.survivalcoding.todolist.ui.main.adapter.OnClickEvent
import com.survivalcoding.todolist.ui.main.adapter.ToDoListAdapter
import java.text.SimpleDateFormat
import java.util.*

class MainFragment : Fragment(), OnClickEvent {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<MainViewModel>()

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

        val adapter by lazy {
            ToDoListAdapter(this).apply {
                stateRestorationPolicy =
                    RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            }
        }

        setTitleTime()

        binding.tvAddTask.setOnClickListener {
            viewModel.insertTask(
                Task(
                    id = Date().time,
                    taskName = "temp task",
                    date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()),
                    isDone = false,
                )
            )
            (activity as MainActivity).replaceFragment(AddEditFragment.newInstance())
        }

        binding.rvTaskList.adapter = adapter

        viewModel.tasks.observe(this) { tasks ->
            adapter.submitList(tasks)
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

    override fun clickEvent(id: Long) {
        viewModel.updateTask(id)
    }

    override fun longClickEvent(id: Long): Boolean {
        viewModel.deleteTask(id)
        return true
    }

    companion object {
        fun newInstance(): MainFragment = MainFragment()
    }
}