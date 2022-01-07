package com.survivalcoding.todolist

import android.app.Application
import com.survivalcoding.todolist.data.datasource.TodoInMemoryDataSource
import com.survivalcoding.todolist.data.repository.TodoRepositoryImpl
import kotlinx.coroutines.Dispatchers

class App : Application() {
    val todoRepository by lazy {
        TodoRepositoryImpl(
            TodoInMemoryDataSource(Dispatchers.IO)
        )
    }
}