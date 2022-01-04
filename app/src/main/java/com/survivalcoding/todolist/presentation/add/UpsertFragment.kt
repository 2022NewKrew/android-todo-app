package com.survivalcoding.todolist.presentation.add


import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.survivalcoding.todolist.R
import com.survivalcoding.todolist.databinding.FragmentAddBinding
import com.survivalcoding.todolist.presentation.MainViewModel
import com.survivalcoding.todolist.presentation.main.MainFragment
import java.text.SimpleDateFormat
import java.util.*

class UpsertFragment : Fragment() {
    private val mainViewModel: MainViewModel by activityViewModels()
    private val upsertViewModel: UpsertViewModel by viewModels()

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = binding.titleEditText
        val content = binding.contentEditText
        val calendar = binding.calendarText

        upsertViewModel.todo.observe(this) { task ->
            calendar.text = SimpleDateFormat("yy/MM/dd", Locale.getDefault()).format(task.date)

            val cal = Calendar.getInstance()
            cal.timeInMillis = task.date
            calendar.setOnClickListener {
                DatePickerDialog(
                    requireContext(),
                    { _, year, monthOfYear, dayOfMonth ->
                        cal.set(Calendar.YEAR, year)
                        cal.set(Calendar.MONTH, monthOfYear)
                        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                        upsertViewModel.updateDate(cal.timeInMillis)
                        calendar.text = SimpleDateFormat(
                            "yy/MM/dd",
                            Locale.getDefault()
                        ).format(cal.timeInMillis)
                    },
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        }
        content.setText(upsertViewModel.getTodo().content)
        title.setText(upsertViewModel.getTodo().title)

        //EditText에 대한 데이터 변경을 알려줌
        title.doAfterTextChanged {
            upsertViewModel.updateTitle(it.toString())
        }
        content.doAfterTextChanged {
            upsertViewModel.updateContent(it.toString())
        }

        val addButton = binding.addButton
        addButton.setOnClickListener {
            if (title.text.isBlank()) {
                Toast.makeText(requireContext(), "제목을 입력하시오", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (upsertViewModel.isEditing()) mainViewModel.updateTodo(upsertViewModel.getTodo())
            else mainViewModel.addTodo(upsertViewModel.getTodo())

            goBackToMainFragment()
        }

        val exitButton = binding.exitButton
        exitButton.setOnClickListener {
            goBackToMainFragment()
        }
    }

    private fun goBackToMainFragment() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view, MainFragment())
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}