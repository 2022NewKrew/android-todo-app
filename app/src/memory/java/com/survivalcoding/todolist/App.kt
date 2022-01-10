package com.survivalcoding.todolist

import android.app.Application
import com.survivalcoding.todolist.data.repository.TodoInMemoryRepository
import com.survivalcoding.todolist.domain.repository.TodoRepository

class App : Application() {
    val todoRepository : TodoRepository = TodoInMemoryRepository()
}