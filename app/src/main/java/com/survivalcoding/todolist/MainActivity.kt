package com.survivalcoding.todolist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val getResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val content = it.data?.getStringExtra("todo")
                if (content != null) {
                    data.add(content)
                }
            }
        }
    private val data = (0..30).map { it.toString() }.toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.fab.setOnClickListener {
            val intent = Intent(this, SimpleTodoWriteActivity::class.java)
            getResult.launch(intent)
        }

        val adapter = SimpleTodoAdapter(data)
        binding.listView.adapter = adapter

        binding.listView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, "$position 번째 item 선택 $id", Toast.LENGTH_SHORT).show()
            (binding.listView.adapter as SimpleTodoAdapter).onClick(view, position)
        }
    }
}