package com.survivalcoding.todolist.presentation.main

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.survivalcoding.todolist.databinding.ActivityMainBinding
import com.survivalcoding.todolist.presentation.createtodo.CreateToDoActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val adapter: ToDoListAdapter by lazy {
        ToDoListAdapter(
            onItemCheckedChanged = viewModel::changeDoneState,
            onDeleteButtonClick = viewModel::deleteToDo
        )
    }

    private val createToDoActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback {
            if (it.resultCode == Activity.RESULT_OK) {
                viewModel.addToDo(
                    it.data?.getParcelableExtra(TODO) ?: return@ActivityResultCallback
                )
            }
        })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.toDoListRecyclerView.adapter = adapter
        binding.createButton.setOnClickListener {
            createToDoActivityResultLauncher.launch(Intent(this, CreateToDoActivity::class.java))
        }

        collect()
    }

    private fun collect() {
        repeatOnStart { viewModel.toDoList.collect { adapter.submitList(it) } }
    }

    private fun repeatOnStart(block: suspend CoroutineScope.() -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED, block)
        }
    }


    companion object {
        const val TODO = "TODO"
    }
}