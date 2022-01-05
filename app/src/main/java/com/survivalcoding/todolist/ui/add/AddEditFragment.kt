package com.survivalcoding.todolist.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.survivalcoding.todolist.databinding.FragmentAddEditBinding
import com.survivalcoding.todolist.domain.entity.Task
import com.survivalcoding.todolist.ui.main.MainFragment
import com.survivalcoding.todolist.ui.main.MainViewModel
import java.util.*

class AddEditFragment : Fragment() {

    private var _binding: FragmentAddEditBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel.getMode() == MainFragment.ADD_MODE) {
            binding.tvAddEditTitle.text = TITLE_ADD_TASK
            binding.etInputTaskName.setText("")
            binding.etInputTaskInfo.setText("")
            binding.tvAddButton.text = BUTTON_ADD_TASK
        } else {
            binding.tvAddEditTitle.text = TITLE_EDIT_TASK
            binding.etInputTaskName.setText(viewModel.getTask().taskName)
            binding.etInputTaskInfo.setText(viewModel.getTask().taskInfo)
            binding.tvAddButton.text = BUTTON_EDIT_TASK
        }

        binding.tvAddButton.setOnClickListener {
            val task = getTask(viewModel.getMode())
            if (task.taskName.isEmpty()) {
                with(requireActivity()) {
                    Toast.makeText(this, "input task name", Toast.LENGTH_SHORT).show()
                }
            } else {
                viewModel.upsertTask(task)
                parentFragmentManager.popBackStack()
            }
        }

        binding.ivBackButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.ivDeleteButton.setOnClickListener {
            viewModel.deleteTask(viewModel.getTask())
            parentFragmentManager.popBackStack()
        }
    }

    private fun getTask(isAddMode: Boolean): Task {
        val taskName = binding.etInputTaskName.text.toString()
        val taskInfo = binding.etInputTaskInfo.text.toString()
        val taskId = if (isAddMode) {
            Date().time
        } else viewModel.getTask().id

        return Task(
            id = taskId,
            taskName = taskName,
            taskInfo = taskInfo,
            date = Date().time
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TITLE_ADD_TASK = "Add new task"
        const val TITLE_EDIT_TASK = "Edit your task"
        const val BUTTON_ADD_TASK = "Add your thing"
        const val BUTTON_EDIT_TASK = "Edit your thing"
    }
}