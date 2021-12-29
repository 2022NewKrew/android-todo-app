package com.survivalcoding.todolist.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.todolist.databinding.ActivityMainBinding
import com.survivalcoding.todolist.main.adapter.TodoListAdapter
import com.survivalcoding.todolist.model.Todo
import java.util.*

class MainActivity : AppCompatActivity() {
    /*
    ViewBinding: View랑 코드랑 바인딩한다
    findViewById를 없애서 관련으로 nullexception 및 타입 관련 에러를 해결
     */
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    var todos = listOf<Todo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val today: Calendar = Calendar.getInstance()

        //1~30 숫자를 매핑해서 Todo형의 list 작성
        todos = (1..30).map{ num ->
            today.add(Calendar.DATE, 1)
            Todo(num.toLong(),"# $num", today.timeInMillis, "${num}번째 내용입니다")
        }

        val adapter = TodoListAdapter(todos)
        val listView = binding.listView
        listView.adapter = adapter

        //ItemClickListener를 지정
        adapter.onItemClicked = { modify ->
            todos = todos.toMutableList().map{ origin ->
                if(origin.id == modify.id) origin.copy(isDone = !origin.isDone)
                else origin
            }
            adapter.submitTodos(todos)
        }
    }
}

