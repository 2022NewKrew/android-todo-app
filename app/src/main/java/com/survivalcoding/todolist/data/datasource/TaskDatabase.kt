package com.survivalcoding.todolist.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.survivalcoding.todolist.domain.entity.Task

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
