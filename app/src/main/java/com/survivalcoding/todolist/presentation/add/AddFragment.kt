package com.survivalcoding.todolist.presentation.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.survivalcoding.todolist.R
import com.survivalcoding.todolist.databinding.FragmentAddBinding
import com.survivalcoding.todolist.presentation.MainViewModel

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel.currentTodo.value == null) { // Add
            binding.addTvTitle.text = getString(R.string.add_title)
            binding.addIvDelete.visibility = View.INVISIBLE
        } else { // Edit
            binding.addTvTitle.text = getString(R.string.edit_title)
            binding.addIvDelete.visibility = View.VISIBLE
        }

        binding.addEtName.setText(viewModel.currentTodo.value?.title)
        binding.addIvBack.setOnClickListener { parentFragmentManager.popBackStack() }

        // 작성 완료 버튼 클릭 시
        binding.addFabComplete.setOnClickListener {
            val title = binding.addEtName.text.toString().trim()

            // 할 일을 입력하지 않은 경우
            if (title.isEmpty()) {
                Toast.makeText(context, getString(R.string.add_todo_empty), Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            if (viewModel.currentTodo.value == null) viewModel.addItem(viewModel.getUpdateTodo(title))
            else viewModel.updateItem(viewModel.getUpdateTodo(title))

            parentFragmentManager.popBackStack()
        }

        // 삭제 버튼 클릭 시
        binding.addIvDelete.setOnClickListener {
            viewModel.currentTodo.value?.let { todo -> viewModel.deleteItem(todo) }
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        viewModel.setTodo(null)
    }
}