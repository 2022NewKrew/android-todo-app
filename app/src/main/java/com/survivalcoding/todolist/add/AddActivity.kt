package com.survivalcoding.todolist.add

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.survivalcoding.todolist.R

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val fab = findViewById<FloatingActionButton>(R.id.add_fab_complete)
        fab.setOnClickListener {
            finish()
        }
    }
}