package com.survivalcoding.todolist.presentation.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.todolist.databinding.ActivityMainBinding
import com.survivalcoding.todolist.model.Todo
import com.survivalcoding.todolist.presentation.main.adapter.TodoListAdapter
import com.survivalcoding.todolist.presentation.todo.TodoActivity

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels()

    private val startTodoActivity =
        registerForActivityResult(StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                when (result.data?.action) {
                    TodoActivity.ACTION_UPSERT -> {
                        val newTodo = result.data?.getParcelableExtra<Todo>(TodoActivity.TODO)!!
                        viewModel.upsertTodo(newTodo)
                    }
                    else -> {
                        Toast.makeText(this, "Unknown Action", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = TodoListAdapter { id ->
            viewModel.todos.value?.find { it.id == id }?.let { selectedTodo ->
                val intent = Intent(this@MainActivity, TodoActivity::class.java)
                    .apply { putExtra(TodoActivity.TODO, selectedTodo) }
                startTodoActivity.launch(intent)
            }
        }

        binding.todoListRv.adapter = adapter
        viewModel.todos.observe(this) { adapter.submitList(it) }

        binding.fab.setOnClickListener {
            startTodoActivity.launch(Intent(this, TodoActivity::class.java))
        }
    }
}