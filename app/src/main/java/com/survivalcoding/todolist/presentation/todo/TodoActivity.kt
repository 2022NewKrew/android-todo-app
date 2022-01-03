package com.survivalcoding.todolist.presentation.todo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.todolist.databinding.ActivityTodoBinding
import com.survivalcoding.todolist.model.Todo
import com.survivalcoding.todolist.toTimestampString

class TodoActivity : AppCompatActivity() {

    companion object {
        const val ACTION_UPSERT = "action_upsert"
        const val TODO = "new_todo"
    }

    private val binding by lazy {
        ActivityTodoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val todo = intent.getParcelableExtra<Todo>(TODO)?.also {
            binding.apply {
                isDoneCb.visibility = View.VISIBLE
                isDoneCb.isChecked = it.isDone
                titleEv.setText(it.title)
                contentEv.setText(it.content)
                timestampTv.visibility = View.VISIBLE
                timestampTv.text = it.timestamp.toTimestampString()
            }
        }

        onBackPressedDispatcher.addCallback(this) {
            val intent = Intent().apply {
                action = ACTION_UPSERT
                putExtra(
                    TODO, Todo(
                        todo?.id ?: -1,
                        binding.titleEv.text.toString(),
                        binding.contentEv.text.toString(),
                        todo?.let { getTimestamp(it) } ?: System.currentTimeMillis(),
                        binding.isDoneCb.isChecked ?: false
                    )
                )
            }

            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    private fun getTimestamp(todo: Todo): Long {
        return binding.run {
            val conditions = listOf(
                isDoneCb.isChecked == todo.isDone,
                titleEv.text.toString() == todo.title,
                contentEv.text.toString() == todo.content
            )

            if (conditions.all { it }) {
                todo.timestamp
            } else {
                System.currentTimeMillis()
            }
        }
    }
}