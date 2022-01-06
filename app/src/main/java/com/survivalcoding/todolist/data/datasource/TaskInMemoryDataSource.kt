package com.survivalcoding.todolist.data.datasource

import androidx.lifecycle.LiveData
import com.survivalcoding.todolist.domain.entity.Task

class TaskInMemoryDataSource(
    private val taskDao: TaskDao
) : TaskDao {
    override fun getAllLive(): LiveData<List<Task>> {
        return taskDao.getAllLive()
    }

    override suspend fun getAllList(): List<Task> {
        return taskDao.getAllList()
    }

    override suspend fun insert(vararg tasks: Task) {
        taskDao.insert(*tasks)
    }

    override suspend fun delete(task: Task) {
        taskDao.delete(task)
    }
}
