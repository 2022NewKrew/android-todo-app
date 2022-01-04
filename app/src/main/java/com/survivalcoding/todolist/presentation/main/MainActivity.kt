package com.survivalcoding.todolist.presentation.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.todolist.R
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

        val adapter = TodoListAdapter(
            onItemClicked = { todo ->
                viewModel.toggleTodo(todo)
            },
            onItemLongClicked = { todo ->
                viewModel.deleteTodo(todo)
            }
        )

        binding.todoRecyclerView.adapter = adapter

        viewModel.todos.observe(this) { todos ->
            adapter.submitList(todos)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.action_add -> {
                viewModel.addTodo("test")
                true
            }
            R.id.action_delete -> {

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}