package com.survivalcoding.todolist.ui.add

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.todolist.databinding.ActivityAddListBinding
import com.survivalcoding.todolist.ui.main.MainActivity
import com.survivalcoding.todolist.model.Task
import java.text.SimpleDateFormat
import java.util.*

class AddListActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddListBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.addNewListButton.setOnClickListener {
            val intent = Intent(this@AddListActivity, MainActivity::class.java)

            val newTask = Task(
                id = Date().time,
                taskName = "추가된 일 ${Date().time % 123}",
                date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()),
                isDone = true
            )
            intent.putExtra(MainActivity.TASK_FROM_REGISTER, newTask)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}