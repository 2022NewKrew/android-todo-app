package com.survivalcoding.todolist.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.survivalcoding.todolist.data.dao.TodoDao
import com.survivalcoding.todolist.domain.model.Todo

@Database(entities = [Todo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}