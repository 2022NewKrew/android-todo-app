package com.survivalcoding.todolist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

const val REQUEST_TAKE_ITEM = 0

class MainActivity : AppCompatActivity() {
    /*
     * Activity Result API 를 적용할 때의 이점
     * 1. Fragment/Activity 의 boilerplate code 가 줄어든다.
     *      OnActivityResult 를 override 할 필요가 없고, requestCode 를 확인하는 일도 없다.
     *      등록한 콜백에서 결과를 처리하기만 하면 된다.
     * 2. Activity 와의 분리
     *      API 는 launcher 에 등록할 때 ActivityResultRegistry 를 사용하지만, 이 외에는 참조 없이 작동이 가능하다.
     *      이로 인하여 깔끔하고 재사용 가능한 구조가 가능하다.
     * 3. 가독성 증가
     *      하나의 launcher 는 하나의 contract 를 실행하고 그 결과를 하나의 callback 으로 반환한다.
     */

    // StartActivityForResult -> 요청한 인텐트로 액티비티를 실행하고, 액티비티 결과를 ActivityResult 로 반환한다.
    private val preContractStartActivityResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { a_result ->
            if (a_result.resultCode == Activity.RESULT_OK) {
                TODO()
            }
        }

    // 해야 할 일 저장하는 리스트
    val toDoList = mutableListOf<Task>()
    val set = mutableSetOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val toDoList = findViewById<ListView>(R.id.toDoListView)        // to_do_list
        val fabAddList =
            findViewById<FloatingActionButton>(R.id.addNewListButton)   // 새로운 항목을 추가하는 버튼

        // 버튼을 눌렀을 때.
        fabAddList.setOnClickListener {
            val intent = Intent(this@MainActivity, AddListActivity::class.java)
            preContractStartActivityResult.launch(intent)
        }

        val data = (1..30).map { Task("$it 번째 일", "2021-12-$it", false) }
        val adapter = ToDoListAdapter(data, set)
        toDoList.adapter = adapter

        // 리스트의 아이템을 눌렀을 때.
        toDoList.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, "${position + 1} 번째 일", Toast.LENGTH_SHORT).show()
            data[position].isDone = !data[position].isDone
        }
    }
}