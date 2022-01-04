package com.survivalcoding.todolist.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.survivalcoding.todolist.databinding.FragmentEditBinding
import com.survivalcoding.todolist.domain.entity.Todo


class EditFragment : Fragment() {
    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addEditText.requestFocus()
        val manager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.showSoftInput(binding.addEditText, 0)

        //뭔가 지저분한 느낌
        var isUpdate = false
        var id: Long = -1
        mainViewModel.isUpdate.value?.let { _isUpdate ->
            if (_isUpdate) {
                isUpdate = _isUpdate
                mainViewModel.todoNeedChanged.value?.let {
                    binding.addEditText.setText(it.title)
                    id = it.id
                }
            }
        }

        binding.addButton.setOnClickListener {
            val inputTodoName = binding.addEditText.text.toString().trim()
            if (inputTodoName.isEmpty()) return@setOnClickListener
            if (isUpdate) {
                mainViewModel.updateTodo(inputTodoName, id)
                //todo 더 우아하게 할 수 있지 않을까?
                mainViewModel.isUpdate.value = false
                mainViewModel.todoNeedChanged.value = null
            } else {
                mainViewModel.addTodo(inputTodoName)
            }
            parentFragmentManager.popBackStack()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}