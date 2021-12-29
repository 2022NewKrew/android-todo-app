package com.survivalcoding.todolist.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.todolist.add.AddActivity
import com.survivalcoding.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 데이터 초기화
        val data = (0..30).map { it.toString() }.toList()

        // 리스트뷰 설정
        binding.mainLvTodo.adapter = TodoAdapter(data)

        binding.mainFabAdd.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }
}