package com.survivalcoding.todolist.ui.add

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.todolist.databinding.ActivityAddListBinding
import com.survivalcoding.todolist.ui.main.MainActivity
import java.util.*

class AddListActivity : AppCompatActivity() {
    private val addTaskViewModel by viewModels<AddViewModel>()
    private val binding by lazy { ActivityAddListBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.addNewListButton.setOnClickListener {
            val intent = Intent(this@AddListActivity, MainActivity::class.java)
            /*
             * 뷰모델에 binding 을 넘겨주는 것이 좋은 방향인지 모르겠다.
             */
            val newTask = addTaskViewModel.makeTask(binding)

            /*
             * 나중에 수정을 위해서 들어오는 경우에, task 데이터가 함께 들어올 것 같다.
             * 확인하는 과정이 필요할 듯.
             */
            intent.putExtra(MainActivity.TASK_FROM_REGISTER, newTask)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}