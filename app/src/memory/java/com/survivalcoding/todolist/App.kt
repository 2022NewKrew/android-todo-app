package com.survivalcoding.todolist

import android.app.Application
import com.survivalcoding.todolist.data.datasource.TaskMockDataSource
import com.survivalcoding.todolist.data.repository.TaskRepositoryImpl

class App : Application() {
    val taskRepository by lazy {
        TaskRepositoryImpl(
            TaskMockDataSource()
        )
    }
}