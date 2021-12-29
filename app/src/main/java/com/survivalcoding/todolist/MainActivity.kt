package com.survivalcoding.todolist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        const val SAVED_TODOS_KEY = "saved_todos_key"
    }

    private val pingPongWriteActivity =
        registerForActivityResult(StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Result OK", Toast.LENGTH_SHORT).show()
            }
        }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var todos: List<Todo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        todos = savedInstanceState?.getParcelableArrayList<Todo>(SAVED_TODOS_KEY)?.toList()
            ?: (1..30).map { Todo("Todo $it", "Content $it", 0L, false) }

        binding.todoListRv.adapter = TodoListAdapter(todos) { position ->
            todos = todos.mapIndexed { index, todo ->
                todo.copy(hasHighlight = if (position == index) !todo.hasHighlight else todo.hasHighlight)
            }

            todos
        }


        binding.fab.setOnClickListener {
            pingPongWriteActivity.launch(Intent(this, WriteActivity::class.java))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(SAVED_TODOS_KEY, ArrayList(todos))
    }
}