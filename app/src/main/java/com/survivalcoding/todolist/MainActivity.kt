package com.survivalcoding.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listNumbers = findViewById<ListView>(R.id.list_numbers)
        listNumbers.adapter = SimpleNumberListAdapter((0..30).toList())
    }
}