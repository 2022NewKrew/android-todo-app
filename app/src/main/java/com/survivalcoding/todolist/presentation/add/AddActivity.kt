package com.survivalcoding.todolist.presentation.add

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.todolist.databinding.ActivityAddBinding
import com.survivalcoding.todolist.model.Todo
import com.survivalcoding.todolist.toTimestampString

class AddActivity : AppCompatActivity() {

    companion object {
        const val NEW_TODO = "new_todo"
    }

    private val binding by lazy {
        ActivityAddBinding.inflate(layoutInflater)
    }

    private val timestamp = System.currentTimeMillis()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.timestampTv.text = timestamp.toTimestampString()

        onBackPressedDispatcher.addCallback(this) {
            val newTodo = Todo(
                -1,
                binding.titleEv.text.toString(),
                binding.contentEv.text.toString(),
                timestamp,
                false
            )

            val intent = Intent().apply {
                action = Intent.ACTION_INSERT
                putExtra(NEW_TODO, newTodo)
            }

            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}