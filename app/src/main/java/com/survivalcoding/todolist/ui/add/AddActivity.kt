package com.survivalcoding.todolist.ui.add

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.survivalcoding.todolist.databinding.ActivityAddBinding
import com.survivalcoding.todolist.ui.main.MainActivity

class AddActivity : AppCompatActivity() {
    private val binding: ActivityAddBinding by lazy {
        ActivityAddBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.addEditText.requestFocus()
/*      포커스에 시간이 필요한 것으로 보임
        val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.showSoftInput(binding.addEditText, 0)
        */


        binding.addButton.setOnClickListener {
            val inputTodoName = binding.addEditText.text.toString().trim()
            if (inputTodoName.isEmpty()) return@setOnClickListener

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(MainActivity.NEW_TODO_ITEM_CODE, inputTodoName)
            setResult(RESULT_OK, intent)
            finish()
        }


    }
}