package com.survivalcoding.todolist.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.survivalcoding.todolist.data.TodoItem
import com.survivalcoding.todolist.databinding.ActivityMainBinding
import com.survivalcoding.todolist.write.SimpleTodoWriteActivity

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val getResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                it.data?.getParcelableExtra<TodoItem>("todo")
                    ?.let { content -> data.add(content) }
            }
        }

    private val data = (0..30.toLong()).map {
        TodoItem(
            id = it,
            title = "title ${it.toInt()}",
            description = "description of task # ${it.toInt()}"
        )
    }.toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = TodoAdapter(data) { id ->
            val newData =
                data.filter { it.id == id }[0].copy(isDone = !data.filter { it.id == id }[0].isDone)
            data[data.indexOf(data.filter { it.id == id }[0])] = newData
        }

        binding.recyclerViewTodoList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewTodoList.adapter = adapter

        binding.fab.setOnClickListener {
            val intent = Intent(this, SimpleTodoWriteActivity::class.java)
            getResult.launch(intent)
        }
    }
}