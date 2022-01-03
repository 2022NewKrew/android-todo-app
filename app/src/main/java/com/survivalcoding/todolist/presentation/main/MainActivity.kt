package com.survivalcoding.todolist.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.todolist.databinding.ActivityMainBinding
import com.survivalcoding.todolist.model.Todo
import com.survivalcoding.todolist.presentation.add.AddActivity
import com.survivalcoding.todolist.presentation.main.adapter.TodoListAdapter

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<MainViewModel>()
    private val adapter by lazy { TodoListAdapter { todo -> viewModel.updateList(todo) } }
    private val addTodo: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            // 작성하기 화면에서 돌아왔을 때
            if (it.resultCode == RESULT_OK && it.data != null) {
                it.data?.extras?.getParcelable<Todo>(TODO_EXTRA_KEY)
                    ?.let { todo ->
                        viewModel.addItem(todo)
                    }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // recyclerView 설정
        binding.mainRvTodo.adapter = adapter

        // 작성하기 화면으로 이동
        binding.mainFabAdd.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            addTodo.launch(intent)
        }

        // todolist 업데이트 관찰
        viewModel.todoList.observe(this) { list ->
            adapter.submitList(list)
        }
    }

    companion object {
        const val TODO_EXTRA_KEY = "todo"
    }
}