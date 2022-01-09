package com.survivalcoding.todolist

import android.app.Application
import androidx.room.Room
import com.survivalcoding.todolist.data.datasource.TaskDatabase
import com.survivalcoding.todolist.data.datasource.TaskInMemoryDataSource
import com.survivalcoding.todolist.data.repository.TaskRepositoryImpl

class App : Application() {
    val taskRepository by lazy {
        TaskRepositoryImpl(
            TaskInMemoryDataSource(
                Room.databaseBuilder(
                    this,
                    TaskDatabase::class.java,
                    "taskDB"
                ).build().taskDao()
            )
        )
    }
}