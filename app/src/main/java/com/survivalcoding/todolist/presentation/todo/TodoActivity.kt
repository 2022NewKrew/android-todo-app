package com.survivalcoding.todolist.presentation.todo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.todolist.R
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

    private val todo by lazy { intent.getParcelableExtra<Todo>(TODO) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        todo?.also {
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_todo, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.delete -> {
                if(todo == null) {
                    setResult(Activity.RESULT_CANCELED)
                    finish()
                } else {
                    val intent = Intent().apply {
                        action = Intent.ACTION_DELETE
                        putExtra(TODO, todo)
                    }
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }
            }
            else -> return false
        }
        return true
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