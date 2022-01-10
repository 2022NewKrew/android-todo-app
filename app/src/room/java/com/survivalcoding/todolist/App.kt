package com.survivalcoding.todolist

import android.app.Application
import androidx.room.Room
import com.survivalcoding.todolist.data.data_source.local.AppDatabase
import com.survivalcoding.todolist.data.repository.TodoRoomRepository
import com.survivalcoding.todolist.domain.repository.TodoRepository

class App : Application() {
    val todoRepository : TodoRepository by lazy {
        TodoRoomRepository(
            Room.databaseBuilder(
                this,
                AppDatabase::class.java, "todo-db"
            ).build().todoDao()
        )
    }
}