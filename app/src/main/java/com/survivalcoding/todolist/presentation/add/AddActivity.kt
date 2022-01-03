package com.survivalcoding.todolist.presentation.add

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.todolist.R
import com.survivalcoding.todolist.databinding.ActivityAddBinding
import com.survivalcoding.todolist.model.Todo
import com.survivalcoding.todolist.presentation.main.MainActivity

class AddActivity : AppCompatActivity() {

    private val binding: ActivityAddBinding by lazy {
        ActivityAddBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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
            // TODO 중복되지 않는 id로 설정
            intent.putExtra(MainActivity.TODO_EXTRA_KEY, Todo(1, title))
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}