package com.survivalcoding.todolist.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.todolist.ui.add.AddListActivity
import com.survivalcoding.todolist.databinding.ActivityMainBinding
import com.survivalcoding.todolist.ui.main.adapter.ToDoListAdapter
import com.survivalcoding.todolist.model.Task
import java.util.*

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

    companion object {
        const val TASK_FROM_REGISTER = "task_from_register"
        const val TASK_FROM_SAVED_INSTANCE_STATE = "task_from_savedInstanceState"
    }

    // view binding
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    // StartActivityForResult -> 요청한 인텐트로 액티비티를 실행하고, 액티비티 결과를 ActivityResult 로 반환한다.
    private val preContractStartActivityResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.getParcelableExtra<Task>(TASK_FROM_REGISTER)?.let {
                    toDoList = toDoList.toMutableList().apply { this.add(0, it) }
                    adapter.submitItem(toDoList)
                }
            }
        }

    // 해야 할 일 저장하는 리스트
    var toDoList = ArrayList<Task>().apply {
        (1..30).forEach {
            this.add(
                Task(
                    id = it.toLong(),
                    taskName = "$it 번째 일",
                    date = "2021-12-$it",
                    isDone = false,
                )
            )
        }
    }.toList()

    // 리사이클러뷰 어댑터
    private val adapter = ToDoListAdapter(toDoList).apply {
        clickEvent = { position, task ->
            toDoList = toDoList.toMutableList().map {
                if (task.id == it.id) task.copy(isDone = !task.isDone)
                else it
            }
            submitItem(toDoList, position)
        }
        // 스크롤 위치 저장
        stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        // 버튼을 눌렀을 때.
        binding.addNewListButton.setOnClickListener {
            val intent = Intent(this@MainActivity, AddListActivity::class.java)
            preContractStartActivityResult.launch(intent)
        }

        binding.toDoListView.adapter = adapter
    }

    // 화면 회전할 때, 데이터 저장
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(TASK_FROM_SAVED_INSTANCE_STATE, ArrayList(toDoList))
    }

    // 화면 회전 후, 데이터 복원
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getParcelableArrayList<Task>(TASK_FROM_SAVED_INSTANCE_STATE)?.let {
            toDoList = it.toList()
            adapter.submitItem(toDoList)
        }
    }

    // 데이터 저장
//    fun dataSave() {
//        val tasksPreferences = getSharedPreferences("tasks", Context.MODE_PRIVATE)
//        tasksPreferences.edit { }
//    }
}