package com.survivalcoding.todolist

import android.app.Application
import androidx.room.Room
import com.survivalcoding.todolist.data.database.AppDatabase
import com.survivalcoding.todolist.data.repository.TodosLocalRepository
import com.survivalcoding.todolist.data.repository.TodosRoomRepository

class App : Application() {
    val todosRepository: TodosLocalRepository by lazy {
        TodosRoomRepository(
            Room.databaseBuilder(
                this,
                AppDatabase::class.java,
                "database"
            ).build().todoDao()
        )
    }
}