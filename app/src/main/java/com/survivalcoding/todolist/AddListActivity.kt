package com.survivalcoding.todolist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AddListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_list)

        supportActionBar?.hide()
    }
}