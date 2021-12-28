package com.survivalcoding.todolist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.todolist.databinding.ActivitySimpleTodoWriteBinding

class SimpleTodoWriteActivity : AppCompatActivity() {
    private var mBinding: ActivitySimpleTodoWriteBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivitySimpleTodoWriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val content = binding.editText.text.toString().trim()

            if (content.length == 0) {
                Toast.makeText(this, "type todo content", Toast.LENGTH_SHORT).show()
            } else {
                intent = Intent()
                intent.putExtra("todo", content)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }
}