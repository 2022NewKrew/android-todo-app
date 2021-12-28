package com.survivalcoding.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.survivalcoding.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // sample data list
        val dataList = (0..30).map { SampleData("title $it") }
        val todoListAdapter = TodoListAdapter(dataList)

        binding.vTodoList.adapter = todoListAdapter


    }

}