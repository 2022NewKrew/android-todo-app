package com.survivalcoding.todolist.add

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.todolist.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {

    private val binding: ActivityAddBinding by lazy {
        ActivityAddBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.addFabComplete.setOnClickListener {
            finish()
        }
    }
}