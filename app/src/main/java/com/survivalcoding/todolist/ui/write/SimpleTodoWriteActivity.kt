package com.survivalcoding.todolist.ui.write

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.todolist.data.model.TodoItem
import com.survivalcoding.todolist.databinding.ActivitySimpleTodoWriteBinding
import com.survivalcoding.todolist.ui.main.MainActivity.Companion.NEW_ID

class SimpleTodoWriteActivity : AppCompatActivity() {
    companion object {
        const val NEW_TODO = "newTodo"
    }

    private val binding by lazy {
        ActivitySimpleTodoWriteBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val newID = intent.getIntExtra(NEW_ID, 0).toLong()

        binding.button.setOnClickListener {
            val title = binding.editTextTitle.text.toString().trim()
            val description = binding.editTextDescription.text.toString().trim()

            if (title.isEmpty()) {
                Toast.makeText(this, "type todo content", Toast.LENGTH_SHORT).show()
            } else {
                intent = Intent()
                intent.putExtra(
                    NEW_TODO,
                    TodoItem(id = newID, title = title, description = description)
                )
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }
}