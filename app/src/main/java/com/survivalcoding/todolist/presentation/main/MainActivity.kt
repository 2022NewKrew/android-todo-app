package com.survivalcoding.todolist.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.todolist.databinding.ActivityMainBinding
import com.survivalcoding.todolist.presentation.main.adapter.TodoListAdapter


class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = TodoListAdapter { todo ->
            viewModel.toggleTodo(todo)
        }

        binding.todoRecyclerView.adapter = adapter

        viewModel.todos.observe(this) { todos ->
            adapter.submitList(null)
            adapter.submitList(todos)
        }
    }

}