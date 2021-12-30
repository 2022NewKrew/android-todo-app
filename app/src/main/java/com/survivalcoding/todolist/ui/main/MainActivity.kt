package com.survivalcoding.todolist.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.survivalcoding.todolist.data.model.TodoItem
import com.survivalcoding.todolist.data.repository.TodoRepository
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
                    ?.let { content -> data.add(content) }
            }
        }

    private val data = TodoRepository.data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        savedInstanceState?.getParcelableArrayList<TodoItem>(SAVED_TODOS)?.let {
            TodoRepository.removeAllData()
            TodoRepository.addAllData(it)
        }

        val adapter = TodoAdapter { id ->
            val prevData = TodoRepository.findDataById(id)
            val newData =
                prevData.copy(isDone = !prevData.isDone)
            TodoRepository.updateDataByIndex(data.indexOf(prevData), newData)
        }

        adapter.submitList(data)
        binding.recyclerViewTodoList.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewTodoList.adapter = adapter

        binding.fab.setOnClickListener {
            val intent = Intent(this, SimpleTodoWriteActivity::class.java)
            intent.putExtra(NEW_ID, data.size)
            getResult.launch(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(SAVED_TODOS, ArrayList(data))
    }
}