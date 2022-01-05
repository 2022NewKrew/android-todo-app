package com.survivalcoding.todolist.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.survivalcoding.todolist.data.dao.ToDoDao
import com.survivalcoding.todolist.data.dto.ToDoRoomDto

@Database(entities = [ToDoRoomDto::class], version = 1)
abstract class ToDoDatabase: RoomDatabase() {
    abstract fun toDoDao(): ToDoDao
}