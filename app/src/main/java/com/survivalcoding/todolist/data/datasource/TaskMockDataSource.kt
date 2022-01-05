package com.survivalcoding.todolist.data.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.survivalcoding.todolist.domain.entity.Task
import java.util.*

class TaskMockDataSource : TaskLocalDataSource {
    private var _tasks =
        (0L..30L).map {
            Task(
                id = it,
                taskName = "$it 번째 일",
                taskInfo = "empty info \nempty info",
                date = Date().time,
                isDone = false
            )
        }.toMutableList()

    override fun getTasksLive(): LiveData<List<Task>> {
        return MutableLiveData(_tasks)
    }

    override fun getTasksList(): List<Task> {
        return _tasks
    }

    override fun deleteTask(newTask: Task) {
        _tasks.remove(newTask)
    }

    override fun upsertTask(newTask: Task) {
        _tasks = _tasks.map {
            if (newTask.id == it.id) newTask
            else it
        }.toMutableList()
    }
}