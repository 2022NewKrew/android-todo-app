package com.survivalcoding.todolist

import android.app.Application
import com.survivalcoding.todolist.data.datasource.TodoLocalDataSource
import com.survivalcoding.todolist.data.local.TodoDatabase
import com.survivalcoding.todolist.data.repository.TodoRepositoryImpl

class App : Application() {
    val todoRepository by lazy {
        TodoRepositoryImpl(
            TodoLocalDataSource(
                TodoDatabase.getDatabase(applicationContext).todoDao()
            )
        )
    }
}