package com.survivalcoding.todolist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val pingPongWriteActivity = registerForActivityResult(StartActivityForResult()) { result ->
        if(result.resultCode == Activity.RESULT_OK) {
            Toast.makeText(this, "Result OK", Toast.LENGTH_SHORT).show()
        }
    }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val todos = (1..30).map { Todo("Todo $it", "Content $it", 0L) }
    private val todoListAdapter = TodoListAdapter(todos).apply { todos }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.todoListRv.adapter = todoListAdapter

        binding.fab.setOnClickListener {
            pingPongWriteActivity.launch(Intent(this, WriteActivity::class.java))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        todoListAdapter.saveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        todoListAdapter.restoreInstanceState(savedInstanceState)
    }
}