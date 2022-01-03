package com.survivalcoding.todolist.presentation.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.todolist.databinding.ActivityMainBinding
import com.survivalcoding.todolist.presentation.add.AddActivity
import com.survivalcoding.todolist.presentation.main.adapter.TodoListAdapter

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels()

    private val pingPongAddActivity =
        registerForActivityResult(StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Result OK", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.todoListRv.adapter =
            TodoListAdapter {
                viewModel.toggleTodos(it)
                submitList(viewModel.todos)
            }.apply { submitList(viewModel.todos) }

        binding.fab.setOnClickListener {
            pingPongAddActivity.launch(Intent(this, AddActivity::class.java))
        }
    }
}