package com.survivalcoding.todolist.ui.main

import android.app.Activity
import androidx.activity.result.ActivityResult
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.todolist.data.TaskRepository
import com.survivalcoding.todolist.model.Task
import com.survivalcoding.todolist.ui.main.adapter.ToDoListAdapter

class MainViewModel : ViewModel() {

    private val taskRepository = TaskRepository

    // TaskRepository 의 데이터
    private val tasks get() = taskRepository.tasks

    // 리사이클러뷰 어댑터
    val adapter = ToDoListAdapter().apply {
        /*
         * position 을 사용해서 clickEvent 를 만들었는데, 아이템 2개를 추가하고 7번 아이템을 클릭했을 때,
         * 5번 아이템이 변경되는 현상이 발생했음.
         * 그래서 확실히 구분되는 id 를 사용하는 방향으로 변경하니 해결됨.
         */
        clickEvent = { id ->
            toggleTask(id)
        }
        longClickEvent = { id ->
            modifyTask(id)
        }
        // 스크롤 위치 저장
        stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
    }

    // AddActivity 의 결과로 하는 일 정의
    val actForAddActivityResult: (ActivityResult, RecyclerView.LayoutManager?) -> Unit =
        { result, layoutManager ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.getParcelableExtra<Task>(MainActivity.TASK_FROM_REGISTER)?.let {
                    addTask(it)
                    /*
                     * 아이템을 추가했을 때, 리사이클러뷰 최상단으로 올라가지 않는 이슈
                     * 역순으로 출력하고, size - 1로 이동하도록 구현했는데 중간 item 을 클릭했을 때, 스크롤이
                     * 움직이는 현상이 생김.
                     */
                    moveToTop(layoutManager)
                    updateTasks()
                }
            }
        }

    private fun toggleTask(id: Long) {
        taskRepository.switchTaskProgressed(id)
        updateTasks()
    }

    private fun modifyTask(id: Long): Boolean {
        /*
         * task 를 길게 눌렀을 때 발생하는 이벤트.
         * 수정을 위한 액티비티 전환 필요할 것 같다.
         *  - task 의 데이터를 가지고 넘어가야 함.
         *  - Id 는 어떻게 할까.
         *  - 날짜는 수정한 시간에 맞춰서 자동으로 바뀌게.
         */
        TODO()
    }

    private fun addTask(newTask: Task) {
        taskRepository.insert(newTask)
        updateTasks()
    }

    fun updateTasks() {
        adapter.submitList(tasks)
    }

    fun moveToTop(layoutManager: RecyclerView.LayoutManager?) {
        layoutManager?.scrollToPosition(0)
    }
}