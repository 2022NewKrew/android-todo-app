package com.survivalcoding.todolist.ui.main

import android.app.Activity
import androidx.activity.result.ActivityResult
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.todolist.data.TaskRepository
import com.survivalcoding.todolist.model.Task
import com.survivalcoding.todolist.ui.main.adapter.ToDoListAdapter

class MainViewModel : ViewModel() {

    private val taskRepository = TaskRepository()

    // TaskRepository 의 데이터
    private val tasks get() = taskRepository.tasks

    // 리사이클러뷰 어댑터
    val adapter = ToDoListAdapter().apply {
        clickEvent = { id ->
            toggleTask(id)
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
                    moveToTop(layoutManager)
                    updateTasks()
                }
            }
        }

    private fun toggleTask(id: Long) {
        taskRepository.switchTaskProgressed(id)
        updateTasks()
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