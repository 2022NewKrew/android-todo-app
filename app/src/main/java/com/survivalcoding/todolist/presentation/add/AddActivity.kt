package com.survivalcoding.todolist.presentation.add

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.todolist.databinding.ActivityAddBinding
import com.survivalcoding.todolist.presentation.main.MainViewModel
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
        title.setText(addViewModel.todo.title)
        val content = binding.contentEditText
        content.setText(addViewModel.todo.content)
        val calendar = binding.calendarText
        calendar.text =
            SimpleDateFormat("yy/MM/dd", Locale.getDefault()).format(addViewModel.todo.date)

        //calendar 터치 이벤트 구현
        val cal = Calendar.getInstance()
        cal.timeInMillis = addViewModel.todo.date
        calendar.setOnClickListener {
            DatePickerDialog(
                this,
                { _, year, monthOfYear, dayOfMonth ->
                    cal.set(Calendar.YEAR, year)
                    cal.set(Calendar.MONTH, monthOfYear)
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    addViewModel.setDate(cal.timeInMillis)
                    calendar.text = SimpleDateFormat(
                        "yy/MM/dd",
                        Locale.getDefault()
                    ).format(addViewModel.todo.date)
                },
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        val addButton = binding.addButton
        addButton.setOnClickListener {
            if(title.text.isBlank()){
                Toast.makeText(this, "제목을 입력하시오", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            addViewModel.updateTodo(
                title.text.toString(),
                addViewModel.todo.date,
                content.text.toString()
            )
            val finIntent = Intent()
            finIntent.putExtra("result", "성공")
            setResult(RESULT_OK, finIntent)
            finish()
        }

        val exitButton = binding.exitButton
        exitButton.setOnClickListener {
            val finIntent = Intent()
            finIntent.putExtra("result", "돌아감")
            setResult(RESULT_OK, finIntent)
            finish()
        }
    }
}