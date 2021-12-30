package com.survivalcoding.todolist.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.survivalcoding.todolist.databinding.ActivityMainBinding
import com.survivalcoding.todolist.domain.TodoListAdapter
import com.survivalcoding.todolist.model.Todo

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val data: MutableList<Todo> =
        (0..30).map { Todo(id = it.toLong(), title = "title $it") }.toMutableList()
    private val todoListAdapter = TodoListAdapter(data).apply {
        // listener 를 생성자에서 생성하지 않고 개별적으로 생성
        onItemClicked = { position ->
            val newItem = Todo(
                data[position].id,
                title = "title ${data[position].id}",
                isDone = !data[position].isDone
            )
            data[position] = newItem
            setItem(newItem, position)
        }
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // enroll recyclerView adapter
        binding.recyclerview.adapter = todoListAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

    }

    override fun onSaveInstanceState(outState: Bundle) {

        outState.putParcelableArrayList("todos", ArrayList(data))
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.getParcelableArrayList<Todo>("todos")?.let {
            data.clear()
            data.addAll(it)
            todoListAdapter.setItems(it)
        }
        super.onRestoreInstanceState(savedInstanceState)
    }

}