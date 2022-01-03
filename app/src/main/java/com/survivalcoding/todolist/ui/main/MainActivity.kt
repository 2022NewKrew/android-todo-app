package com.survivalcoding.todolist.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.todolist.databinding.ActivityMainBinding
import com.survivalcoding.todolist.domain.entity.Task
import com.survivalcoding.todolist.ui.add.AddListActivity
import com.survivalcoding.todolist.ui.main.adapter.OnClickEvent
import com.survivalcoding.todolist.ui.main.adapter.ToDoListAdapter
import java.util.*

class MainActivity : AppCompatActivity(), OnClickEvent {
    companion object {
        const val TASK_FROM_REGISTER = "task_from_register"
    }

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val mainViewModel by viewModels<MainViewModel>()

    private val adapter by lazy {
        ToDoListAdapter(this).apply {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
    }

    private val actForAddActivityResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.getParcelableExtra<Task>(TASK_FROM_REGISTER)?.let {
                    mainViewModel.insertTask(it)
                    Toast.makeText(this, "task added ${mainViewModel.tasks}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.toDoListView.adapter = adapter

        mainViewModel.tasks.observe(this, { tasks ->
            adapter.submitList(tasks)
        })

        binding.addNewListButton.setOnClickListener {
            val intent = Intent(this@MainActivity, AddListActivity::class.java)
            actForAddActivityResult.launch(intent)
        }
    }

    override fun clickEvent(id: Long) {
        mainViewModel.updateTask(id)
        Toast.makeText(this, "isChanged", Toast.LENGTH_SHORT).show()
    }

    override fun longClickEvent(id: Long): Boolean {
        Toast.makeText(this, "not yet", Toast.LENGTH_SHORT).show()
        return true
    }
}