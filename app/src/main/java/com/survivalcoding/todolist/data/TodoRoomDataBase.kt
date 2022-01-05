package com.survivalcoding.todolist.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TodoModel::class], version = 1)
abstract class TodoRoomDataBase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "todo_database"
    }
    abstract fun todoDao(): TodoDao
}