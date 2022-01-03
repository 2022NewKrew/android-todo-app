package com.survivalcoding.todolist.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.survivalcoding.todolist.databinding.ActivityMainBinding
import com.survivalcoding.todolist.domain.model.Todo
import com.survivalcoding.todolist.presentation.add.AddActivity
import com.survivalcoding.todolist.presentation.main.adapter.TodoListAdapter

class MainActivity : AppCompatActivity() {
    /*
    ViewBinding: View랑 코드랑 바인딩한다
    findViewById를 없애서 관련으로 nullException 및 타입 관련 에러를 해결
     */
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //ResultLauncher 정의
        val resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {

                    //let을 2개 써서 더러워보임, 뭔가 멋있는 방법이 없나?
                    result.data?.extras?.getParcelable<Todo>("data")?.let { todo ->
                        result.data?.extras!!.getBoolean("isModifying").let { isModifying ->
                            if (isModifying) mainViewModel.updateTodo(todo)
                            else mainViewModel.addTodo(todo)
                        }
                    }
                }
            }

        val adapter = TodoListAdapter(onChangeIsDone = { modify ->
            mainViewModel.toggleTodo(modify)
        }, onModifyTodo = {
            val intent = Intent(this, AddActivity::class.java)
            intent.putExtra("modify", it)
            resultLauncher.launch(intent)
        })

        val recyclerView = binding.todoRecyclerView

        recyclerView.adapter = adapter
        // RecyclerView에 layoutManager 지정으로 설정 가능
        recyclerView.layoutManager = LinearLayoutManager(this)

        //UI를 변경하는 부분을 관찰할 수 있게 확인
        mainViewModel.todos.observe(this) { todos ->
            adapter.submitList(todos)
        }

        //Add Button을 통해 다른 액티비티로 이동
        val addButton = binding.addButton
        addButton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            resultLauncher.launch(intent)
        }
    }
}

