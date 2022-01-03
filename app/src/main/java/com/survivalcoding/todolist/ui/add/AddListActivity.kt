package com.survivalcoding.todolist.ui.add

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.todolist.databinding.ActivityAddListBinding
import com.survivalcoding.todolist.ui.main.MainActivity

class AddListActivity : AppCompatActivity() {
    private val addTaskViewModel by viewModels<AddViewModel>()
    private val binding by lazy { ActivityAddListBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.addNewListButton.setOnClickListener {
            val intent = Intent()

            val newTask = addTaskViewModel.makeTask(binding)

            intent.putExtra(MainActivity.TASK_FROM_REGISTER, newTask)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}