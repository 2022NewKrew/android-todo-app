package com.survivalcoding.todolist.presentation.add

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.todolist.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    private val binding : ActivityAddBinding by lazy{
        ActivityAddBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val exitButton = binding.exitButton
        exitButton.setOnClickListener {
            val finIntent = Intent()
            finIntent.putExtra("result", "성공")
            setResult(RESULT_OK, finIntent)
            finish()
        }
    }
}