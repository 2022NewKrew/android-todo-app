package com.survivalcoding.todolist.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.todolist.databinding.ActivityMainBinding
import com.survivalcoding.todolist.presentation.add.AddActivity
import com.survivalcoding.todolist.presentation.main.adapter.TodoListAdapter

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val adapter by lazy {
        TodoListAdapter().apply {
            // 초기 데이터 설정
            submitList(viewModel.todoList)

            // 클릭 리스너 설정
            itemClickListener = { todo ->
                viewModel.updateList(todo)
                submitList(viewModel.todoList)
            }
        }
    }
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // recyclerView 설정
        binding.mainRvTodo.adapter = adapter

        binding.mainFabAdd.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }
}