package com.survivalcoding.todolist.ui.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.survivalcoding.todolist.databinding.ActivityMainBinding
import com.survivalcoding.todolist.ui.add.AddActivity
import com.survivalcoding.todolist.ui.main.adapter.TodoListAdapter

class MainActivity : AppCompatActivity() {

    companion object {
        val NEW_TODO_ITEM_CODE = "NEW_TODO_ITEM"
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val mainViewModel by viewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val todoListAdapter = TodoListAdapter { item ->
            mainViewModel.toggleIsDone(item)
        }

        // enroll listAdapter
        binding.recyclerview.adapter = todoListAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        mainViewModel.todos.observe(this, { todos ->
            todoListAdapter.submitList(todos)
        })


        // to add activity
        val activityResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    it.data?.let { intent ->
                        intent.getStringExtra(NEW_TODO_ITEM_CODE)?.let { title ->
                            mainViewModel.addTodo(title)
                        }
                    }
                }
            }

        binding.fab.setOnClickListener {
            activityResult.launch(Intent(this, AddActivity::class.java))
        }


    }

}