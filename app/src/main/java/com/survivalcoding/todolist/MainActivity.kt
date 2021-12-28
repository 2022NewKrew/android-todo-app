package com.survivalcoding.todolist

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

const val REQUEST_SELECT_CONTACT = 1

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.hello_text).setOnClickListener {
            startActivityForResult(
                Intent(this, AddTodoActivity::class.java),
                REQUEST_SELECT_CONTACT
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_SELECT_CONTACT && resultCode == RESULT_OK) {
            if (data != null) {
                val result = data.getStringExtra("result")
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
            }
        }
    }
}