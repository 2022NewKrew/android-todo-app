package com.survivalcoding.todolist.data.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.survivalcoding.todolist.domain.entity.Task
import java.util.*

class TaskMockDataSource : TaskDao {
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

    override fun getAllLive(): LiveData<List<Task>> {
        return MutableLiveData(_tasks)
    }

    override suspend fun getAllList(): List<Task> {
        return _tasks
    }

    override suspend fun insert(vararg tasks: Task) {
        tasks.forEach { newTask ->
            _tasks = _tasks.map {
                if (it.id == newTask.id) newTask
                else it
            }.toMutableList()
        }
    }

    override suspend fun delete(task: Task) {
        _tasks.remove(task)
    }
}