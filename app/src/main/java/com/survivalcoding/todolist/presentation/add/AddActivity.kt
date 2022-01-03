package com.survivalcoding.todolist.presentation.add

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.todolist.R

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val finishBt = findViewById<Button>(R.id.finishBt)
        finishBt.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}