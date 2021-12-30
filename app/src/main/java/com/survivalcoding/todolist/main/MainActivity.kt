package com.survivalcoding.todolist.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.todolist.databinding.ActivityMainBinding
import com.survivalcoding.todolist.main.adapter.TodoListAdapter
import com.survivalcoding.todolist.model.Todo


class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    // 변경 안되는 리스트
    var todos = listOf<Todo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        todos = (0..30).map { num ->
            Todo(
                id = num.toLong(),
                title = "청소 $num",
            )
        }.toList()

        val adapter = TodoListAdapter()
        adapter.onItemClicked = { todo ->
            todos = todos.toMutableList().map {
                if (it.id == todo.id) {
                    todo.copy(isDone = !todo.isDone)
                } else {
                    it
                }
            }
            adapter.submitList(todos)
        }

        binding.todoRecyclerView.adapter = adapter

        if (savedInstanceState != null) {
            savedInstanceState.getParcelableArrayList<Todo>("todos")?.let {
                todos = it
            }
        }

        adapter.submitList(todos)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelableArrayList("todos", ArrayList(todos))
    }

//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//
//        savedInstanceState.getParcelableArrayList<Todo>("todos")?.let {
//            todos = it
//        }
//    }

}