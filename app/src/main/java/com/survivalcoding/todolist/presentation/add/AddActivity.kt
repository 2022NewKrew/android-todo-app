package com.survivalcoding.todolist.presentation.add

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.todolist.R
import com.survivalcoding.todolist.databinding.ActivityAddBinding
import com.survivalcoding.todolist.model.Todo
import com.survivalcoding.todolist.presentation.main.MainActivity.Companion.REMOVE_STATUS_KEY
import com.survivalcoding.todolist.presentation.main.MainActivity.Companion.TODO_EXTRA_KEY

class AddActivity : AppCompatActivity() {

    private val binding: ActivityAddBinding by lazy {
        ActivityAddBinding.inflate(layoutInflater)
    }
    private val viewModel by viewModels<AddViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (intent.getParcelableExtra<Todo>(TODO_EXTRA_KEY) == null) {
            binding.addTvTitle.text = getString(R.string.add_title)
            binding.addIvDelete.visibility = View.INVISIBLE
        } else {
            binding.addTvTitle.text = getString(R.string.edit_title)
            binding.addIvDelete.visibility = View.VISIBLE
        }

        val todo = intent.getParcelableExtra(TODO_EXTRA_KEY)
            ?: Todo((viewModel.todoList.value?.last()?.id ?: 0) + 1, "")

        binding.addEtName.setText(todo.title)
        binding.addIvBack.setOnClickListener { finish() }

        // 작성 완료 버튼 클릭 시
        binding.addFabComplete.setOnClickListener {
            val title = binding.addEtName.text.toString().trim()

            // 할 일을 입력하지 않은 경우
            if (title.isEmpty()) {
                Toast.makeText(this, getString(R.string.add_todo_empty), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent()
            intent.putExtra(
                TODO_EXTRA_KEY,
                todo.copy(id = todo.id, title = title, isDone = todo.isDone)
            )
            setResult(RESULT_OK, intent)
            finish()
        }

        // 삭제 버튼 클릭 시
        binding.addIvDelete.setOnClickListener {
            val intent = Intent()
            intent.putExtra(TODO_EXTRA_KEY, todo)
            intent.putExtra(REMOVE_STATUS_KEY, true)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}