package com.survivalcoding.todolist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ListView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val pingPongWriteActivity = registerForActivityResult(StartActivityForResult()) { result ->
        if(result.resultCode == Activity.RESULT_OK) {
            Toast.makeText(this, "Result OK", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            pingPongWriteActivity.launch(Intent(this, WriteActivity::class.java))
        }
    }
}