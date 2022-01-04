package com.survivalcoding.todolist.presentation.add

/*
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.survivalcoding.todolist.databinding.FragmentAddBinding
import com.survivalcoding.todolist.presentation.MainViewModel
import java.text.SimpleDateFormat
import java.util.*

class AddFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentAddBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
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

        viewModel.todo.observe(this) { task ->
            /*  EditView는 작동하지 않음, Spannable이 String으로 캐스팅 불가한다고 뜬다.
            title.setText(task.title)
            content.setText(task.content)
             */
            calendar.text = SimpleDateFormat("yy/MM/dd", Locale.getDefault()).format(task.date)
        }
        //따로 설정 - EditText만의 특이점이 있는 모양
        content.setText(viewModel.getTodo().content)
        title.setText(viewModel.getTodo().title)

        //EditText에 대한 데이터 변경을 알려줌
        title.doAfterTextChanged {
            viewModel.updateTitle(it.toString())
        }
        content.doAfterTextChanged {
            viewModel.updateContent(it.toString())
        }

        //calendar 터치 이벤트 구현
        val cal = Calendar.getInstance()
        cal.timeInMillis = viewModel.todo.value!!.date
        calendar.setOnClickListener {
            DatePickerDialog(
                this,
                { _, year, monthOfYear, dayOfMonth ->
                    cal.set(Calendar.YEAR, year)
                    cal.set(Calendar.MONTH, monthOfYear)
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    viewModel.updateDate(cal.timeInMillis)
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


        val addButton = binding.addButton
        addButton.setOnClickListener {
            if (title.text.isBlank()) {
                Toast.makeText(requireContext(), "제목을 입력하시오", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //Todo: 다른 프래그먼트로 교체 작업
        }

        val exitButton = binding.exitButton
        exitButton.setOnClickListener {
            //Todo: 다른 프래그먼트로 교체 작업
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}*/