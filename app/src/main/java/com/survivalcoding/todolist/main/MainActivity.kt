package com.survivalcoding.todolist.main

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.survivalcoding.todolist.R
import com.survivalcoding.todolist.add.AddActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 데이터 초기화
        val data = (0..30).map { it.toString() }.toList()

        // 리스트뷰 설정
        val listView = findViewById<ListView>(R.id.main_lv_todo)
        listView.adapter = TodoAdapter(data)

        val fab = findViewById<FloatingActionButton>(R.id.main_fab_add)
        fab.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }
}