package com.survivalcoding.todolist.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.survivalcoding.todolist.databinding.ActivityMainBinding
import com.survivalcoding.todolist.main.adapter.TodoListAdapter
import com.survivalcoding.todolist.model.Todo
import java.util.*
import kotlin.collections.ArrayList

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

        //회전하는 경우 번들에서 받으면 된다
        todos = if (savedInstanceState?.getParcelableArrayList<Todo>("todoList") != null) {
            savedInstanceState.getParcelableArrayList("todoList")!!
        } else { // 처음 받을 시 1~30 숫자를 매핑해서 Todo형의 list 작성
            (1..30).map { num ->
                today.add(Calendar.DATE, 1)
                Todo(num.toLong(), "# $num", today.timeInMillis, "${num}번째 내용입니다")
            }
        }

        val adapter = TodoListAdapter(todos)
        val recyclerView = binding.todoRecyclerView
        recyclerView.adapter = adapter
        // RecyclerView에 layoutManager 지정으로 설정 가능
        recyclerView.layoutManager = LinearLayoutManager(this)

        // ItemClickListener를 지정
        adapter.onItemClicked = { modify, position ->
            todos = todos.toMutableList().map { origin ->
                if (origin.id == modify.id) origin.copy(isDone = !origin.isDone)
                else origin
            }
            adapter.submitTodos(todos, position)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("todoList", ArrayList(todos))
    }
    /*
    // 호출 시점: onCreate 직후
    // 이 방법의 경우 todos의 데이터를 다시 업데이트를 해야하는 문제를 안고 있음.
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }
     */
}

