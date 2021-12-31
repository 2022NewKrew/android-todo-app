package com.survivalcoding.todolist.presentation.createtodo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.survivalcoding.todolist.databinding.ActivityCreateToDoBinding
import com.survivalcoding.todolist.presentation.main.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CreateToDoActivity : AppCompatActivity() {

    private val viewModel: CreateToDoViewModel by viewModels()

    private val binding: ActivityCreateToDoBinding by lazy {
        ActivityCreateToDoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            viewModel.createNewToDo(binding.newToDoEditText.text.toString())
        }

        collect()
    }

    private fun collect() {
        repeatOnStart { viewModel.newToDoCreatedEvent.collect {
            setResult(Activity.RESULT_OK, Intent().putExtra(MainActivity.TODO, it))
            finish()
        } }
    }

    private fun repeatOnStart(block: suspend CoroutineScope.() -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED, block)
        }
    }
}