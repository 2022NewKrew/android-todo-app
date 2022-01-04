package com.survivalcoding.todolist.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.todolist.domain.models.TodoItem
import com.survivalcoding.todolist.databinding.ActivityMainBinding
import com.survivalcoding.todolist.ui.write.SimpleTodoWriteActivity
import com.survivalcoding.todolist.ui.write.SimpleTodoWriteActivity.Companion.NEW_TODO

class MainActivity : AppCompatActivity() {
    companion object {
        private const val SAVED_TODOS = "todos"
        const val NEW_ID = "newID"
    }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val getResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                it.data?.getParcelableExtra<TodoItem>(NEW_TODO)
                    ?.let { content -> viewModel.addTodo(content) }
            }
        }

    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        savedInstanceState?.getParcelableArrayList<TodoItem>(SAVED_TODOS)?.let {
            viewModel.setTodos(it)
        }

        val adapter = TodoAdapter(viewModel::toggleTodoDone)

        binding.recyclerViewTodoList.adapter = adapter

        viewModel.data.observe(this) { todos ->
            adapter.submitList(todos)
        }

        binding.fab.setOnClickListener {
            val intent = Intent(this, SimpleTodoWriteActivity::class.java)
            intent.putExtra(NEW_ID, viewModel.data.value?.size)
            getResult.launch(intent)
        }
    }
}