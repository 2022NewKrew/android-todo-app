package com.survivalcoding.todolist.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.todolist.databinding.ActivityMainBinding
import com.survivalcoding.todolist.ui.add.AddListActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val TASK_FROM_REGISTER = "task_from_register"
    }

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mainViewModel by viewModels<MainViewModel>()
    private val actForAddActivityResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            mainViewModel.actForAddActivityResult(it, binding.toDoListView.layoutManager)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.addNewListButton.setOnClickListener {
            val intent = Intent(this@MainActivity, AddListActivity::class.java)
            actForAddActivityResult.launch(intent)
        }

        binding.toDoListView.adapter = mainViewModel.adapter
        mainViewModel.updateTasks()
        mainViewModel.moveToTop(binding.toDoListView.layoutManager)
    }
}