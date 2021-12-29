package com.survivalcoding.todolist.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.todolist.add.AddActivity
import com.survivalcoding.todolist.databinding.ActivityMainBinding
import com.survivalcoding.todolist.main.adapter.TodoListAdapter
import com.survivalcoding.todolist.model.Todo

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var todoList: List<Todo> =
        (0..30).map { num -> Todo(id = num.toLong(), title = "할 일 $num") }.toList()
    private val adapter by lazy {
        TodoListAdapter().apply {
            // 초기 데이터 설정
            setItems(todoList)

            // 클릭 리스너 설정
            setItemClickListener { todo ->
                todoList = todoList.toMutableList().map {
                    if (it.id == todo.id) todo.copy(isDone = !todo.isDone)
                    else it
                }
                setItems(todoList)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // recyclerView 설정
        binding.mainRvTodo.adapter = adapter

        binding.mainFabAdd.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        savedInstanceState.getParcelableArrayList<Todo>("data")?.toList()?.let {
            todoList = it
            adapter.setItems(todoList)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelableArrayList("data", ArrayList(todoList))
    }
}