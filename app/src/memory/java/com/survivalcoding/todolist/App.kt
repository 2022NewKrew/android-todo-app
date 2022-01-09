package com.survivalcoding.todolist

import android.app.Application
import com.survivalcoding.todolist.data.repository.TodosInMemoryRepository
import com.survivalcoding.todolist.data.repository.TodosLocalRepository

class App : Application() {
    val todosRepository: TodosLocalRepository = TodosInMemoryRepository()
}