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
            if (_tasks.any { it.id == newTask.id }) {
                val oldTask = _tasks.filter { it.id == newTask.id }[0]
                _tasks.remove(oldTask)
                _tasks.add(newTask)
            } else {
                _tasks.add(0, newTask)
            }
        }
    }

    override suspend fun delete(task: Task) {
        _tasks.remove(task)
    }
}