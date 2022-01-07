package com.survivalcoding.todolist

import android.app.Application
import androidx.room.Room
import com.survivalcoding.todolist.data.TodoRoomRepositoryImpl
import com.survivalcoding.todolist.data.TodoRoomDataBase


class MyApp : Application() {

    val todoRepository by lazy {
        TodoRoomRepositoryImpl(
            Room.databaseBuilder(
                baseContext,
                TodoRoomDataBase::class.java, TodoRoomDataBase.DATABASE_NAME
            ).build()
        )
    }

    override fun onCreate() {

        super.onCreate()
    }

}