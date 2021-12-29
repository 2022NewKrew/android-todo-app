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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // recyclerView 설정
        val adapter = TodoListAdapter().apply {
            // 초기 데이터 설정
            setItems(todoList)

            // 클릭 리스너 설정
            setItemClickListener { todo ->
                val newTodoList = todoList.toMutableList().map {
                    if (it.id == todo.id) todo.copy(isDone = !todo.isDone)
                    else it
                }

                todoList = newTodoList
                setItems(todoList)
            }
        }

        binding.mainRvTodo.adapter = adapter

        binding.mainFabAdd.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }
}