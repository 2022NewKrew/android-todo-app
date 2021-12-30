package com.survivalcoding.todolist.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.survivalcoding.todolist.databinding.ActivityMainBinding
import com.survivalcoding.todolist.ui.main.adapter.TodoListAdapter

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val mainViewModel by viewModels<MainViewModel>()
    private val todoListAdapter = TodoListAdapter().apply {
        onItemClicked = { position ->
            mainViewModel.toggleIsDone(position)
            submitList(mainViewModel.get())
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // enroll listAdapter
        todoListAdapter.submitList(mainViewModel.get())
        binding.recyclerview.adapter = todoListAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)


    }

}