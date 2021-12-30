package com.survivalcoding.todolist.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.survivalcoding.todolist.add.AddActivity
import com.survivalcoding.todolist.data.TodoRepository
import com.survivalcoding.todolist.databinding.ActivityMainBinding
import com.survivalcoding.todolist.main.adapter.TodoListAdapter
import com.survivalcoding.todolist.model.Todo
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    /*
    ViewBinding: View랑 코드랑 바인딩한다
    findViewById를 없애서 관련으로 nullException 및 타입 관련 에러를 해결
     */
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val todoRepository = TodoRepository()
    private val adapter = TodoListAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //ResultLauncher 정의
        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == RESULT_OK){
                val data = result.data
                Toast.makeText(this, data!!.extras!!.getString("result"), Toast.LENGTH_SHORT).show()
            }
        }

        val recyclerView = binding.todoRecyclerView
        recyclerView.adapter = adapter
        // RecyclerView에 layoutManager 지정으로 설정 가능
        recyclerView.layoutManager = LinearLayoutManager(this)

        // ItemClickListener를 지정
        adapter.onItemClicked = { modify ->
            todoRepository.updateTodos(modify)
            adapter.submitList(todoRepository.todos)
        }

        adapter.submitList(todoRepository.todos)

        //Add Button을 통해 다른 액티비티로 이동
        val addButton = binding.addButton
        addButton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("todoList", ArrayList(todoRepository.todos))
    }
    // 호출 시점: onCreate 직후
    // 이 방법의 경우 todos의 데이터를 다시 업데이트를 해야하는 문제를 안고 있음.
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getParcelableArrayList<Todo>("todoList")?.let{
            todoRepository.todos = it
            adapter.submitList(todoRepository.todos)
        }
    }
}

