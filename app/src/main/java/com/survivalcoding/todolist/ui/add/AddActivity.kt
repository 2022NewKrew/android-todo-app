package com.survivalcoding.todolist.ui.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.survivalcoding.todolist.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    private val binding: ActivityAddBinding by lazy {
        ActivityAddBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}