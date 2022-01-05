package com.survivalcoding.todolist.data.datasource

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.survivalcoding.todolist.domain.entity.Task
import com.survivalcoding.todolist.domain.entity.TaskDatabase

class TaskInMemoryDataSource(val context: Context) : TaskLocalDataSource {
    private val db = Room.databaseBuilder(
        context,
        TaskDatabase::class.java,
        "taskDB"
    ).allowMainThreadQueries().build()

    private val _tasks
        get() = db.taskDao().getAll()

    override fun getTasks(): LiveData<List<Task>> {
        return _tasks
    }

    override fun deleteTask(newTask: Task) {
        db.taskDao().delete(newTask)
    }

    override fun upsertTask(newTask: Task) {
        db.taskDao().insert(newTask)
    }
}