package com.survivalcoding.todolist

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class WriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        val finishBt = findViewById<Button>(R.id.finishBt)
        finishBt.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}