package com.survivalcoding.todolist.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.todolist.data.TodoRepository
import com.survivalcoding.todolist.databinding.ActivityMainBinding
import com.survivalcoding.todolist.model.Todo
import com.survivalcoding.todolist.presentation.main.adapter.TodoListAdapter


class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val todoRepository = TodoRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = TodoListAdapter()
        adapter.onItemClicked = { todo ->
            todoRepository.updateTodo(todo)
            adapter.submitList(todoRepository.todos)
        }

        binding.todoRecyclerView.adapter = adapter

        if (savedInstanceState != null) {
            savedInstanceState.getParcelableArrayList<Todo>("todos")?.let {
                todoRepository.todos = it
            }
        }

        adapter.submitList(todoRepository.todos)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelableArrayList("todos", ArrayList(todoRepository.todos))
    }

//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//
//        savedInstanceState.getParcelableArrayList<Todo>("todos")?.let {
//            todos = it
//        }
//    }

}