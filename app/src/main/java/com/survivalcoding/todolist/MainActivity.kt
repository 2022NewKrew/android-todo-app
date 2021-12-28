package com.survivalcoding.todolist

import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = (0..30).map { it.toString() }.toList()

//        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
        val adapter = MyFirstAdapter(data)

        val listView = findViewById<ListView>(R.id.list_view)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, "$position 번째 아이템 선택 $id", Toast.LENGTH_SHORT).show()
//            view.setBackgroundColor(Color.RED)
        }
    }

}