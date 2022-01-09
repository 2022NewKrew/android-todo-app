package com.survivalcoding.todolist

import android.app.Application
import com.survivalcoding.todolist.data.TodoInMemoryRepositoryImpl

class MyApp : Application() {

    val todoRepository = TodoInMemoryRepositoryImpl()

    override fun onCreate() {

        super.onCreate()
    }

}