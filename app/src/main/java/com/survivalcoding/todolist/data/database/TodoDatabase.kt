package com.survivalcoding.todolist.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.survivalcoding.todolist.data.dao.TodoDao
import com.survivalcoding.todolist.domain.models.TodoItem

@Database(entities = [TodoItem::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}