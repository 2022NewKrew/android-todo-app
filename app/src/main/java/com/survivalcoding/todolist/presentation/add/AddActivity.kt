package com.survivalcoding.todolist.presentation.add

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.survivalcoding.todolist.databinding.ActivityAddBinding
import java.text.SimpleDateFormat
import java.util.*

class AddActivity : AppCompatActivity() {
    private val binding: ActivityAddBinding by lazy {
        ActivityAddBinding.inflate(layoutInflater)
    }

    private val addViewModel: AddViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val title = binding.titleEditText
        val content = binding.contentEditText
        val calendar = binding.calendarText

        addViewModel.todo.observe(this) { task ->
            /*  EditView는 작동하지 않음, Spannable이 String으로 캐스팅 불가한다고 뜬다.
            title.setText(task.title)
            content.setText(task.content)
             */
            calendar.text = SimpleDateFormat("yy/MM/dd", Locale.getDefault()).format(task.date)
        }
        //따로 설정 - EditText만의 특이점이 있는 모양
        content.setText(addViewModel.getTodo().content)
        title.setText(addViewModel.getTodo().title)

        //EditText에 대한 데이터 변경을 알려줌
        title.doAfterTextChanged {
            addViewModel.updateTitle(it.toString())
        }
        content.doAfterTextChanged {
            addViewModel.updateContent(it.toString())
        }

        //calendar 터치 이벤트 구현
        val cal = Calendar.getInstance()
        cal.timeInMillis = addViewModel.todo.value!!.date
        calendar.setOnClickListener {
            DatePickerDialog(
                this,
                { _, year, monthOfYear, dayOfMonth ->
                    cal.set(Calendar.YEAR, year)
                    cal.set(Calendar.MONTH, monthOfYear)
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    addViewModel.updateDate(cal.timeInMillis)
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
                Toast.makeText(this, "제목을 입력하시오", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val finIntent = Intent()
            finIntent.putExtra("data", addViewModel.getTodo())
            finIntent.putExtra("isModifying", addViewModel.getIsModifying())
            setResult(RESULT_OK, finIntent)
            finish()
        }

        val exitButton = binding.exitButton
        exitButton.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }
    }
}