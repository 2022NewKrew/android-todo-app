package com.survivalcoding.todolist

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.survivalcoding.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!!
    private lateinit var getResult: ActivityResultLauncher<Intent>
    private val data = (0..30).map { it.toString() }.toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val content = it.data?.getStringExtra("todo")
                if (content!= null) {
                    data.add(content)
                }
            }
        }

        binding.fab.setOnClickListener {
            val intent = Intent(this, SimpleTodoWriteActivity::class.java)
            getResult.launch(intent)
        }

        Log.d("aaa", "aaa")

        val adapter = SimpleTodoAdapter(data)
        binding.listView.adapter = adapter

        Log.d("aaa", "bbb")

        binding.listView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, "$position 번째 item 선택 $id", Toast.LENGTH_SHORT).show()
            (binding.listView.adapter as SimpleTodoAdapter).onClick(view, position)
        }

        Log.d("aaa", "ccc")
    }
}