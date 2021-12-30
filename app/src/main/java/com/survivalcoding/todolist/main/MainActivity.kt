package com.survivalcoding.todolist.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.survivalcoding.todolist.add.AddActivity
import com.survivalcoding.todolist.databinding.ActivityMainBinding
import com.survivalcoding.todolist.main.adapter.TodoListAdapter
import com.survivalcoding.todolist.presentation.MainViewModel

class MainActivity : AppCompatActivity() {
    /*
    ViewBinding: View랑 코드랑 바인딩한다
    findViewById를 없애서 관련으로 nullException 및 타입 관련 에러를 해결
     */
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val adapter = TodoListAdapter()
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //ResultLauncher 정의
        val resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val data = result.data
                    Toast.makeText(this, data!!.extras!!.getString("result"), Toast.LENGTH_SHORT)
                        .show()
                }
            }


        val recyclerView = binding.todoRecyclerView
        recyclerView.adapter = adapter
        // RecyclerView에 layoutManager 지정으로 설정 가능
        recyclerView.layoutManager = LinearLayoutManager(this)

        // ItemClickListener를 지정
        adapter.onItemClicked = { modify ->
            mainViewModel.toggleTodo(modify)
            adapter.submitList(mainViewModel.todos)
        }

        adapter.submitList(mainViewModel.todos)

        //Add Button을 통해 다른 액티비티로 이동
        val addButton = binding.addButton
        addButton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            resultLauncher.launch(intent)
        }
    }
}

