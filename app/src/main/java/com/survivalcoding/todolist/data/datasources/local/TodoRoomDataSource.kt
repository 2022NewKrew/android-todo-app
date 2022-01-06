package com.survivalcoding.todolist.data.datasources.local

import android.content.Context
import androidx.room.Room
import com.survivalcoding.todolist.data.database.TodoDatabase
import com.survivalcoding.todolist.domain.interfaces.TodoDataSource
import com.survivalcoding.todolist.domain.models.TodoItem

class TodoRoomDataSource(appContext: Context) : TodoDataSource {
    private val _db = Room.databaseBuilder(
        appContext,
        TodoDatabase::class.java, "todo-database"
    ).allowMainThreadQueries()
        .build()

    override fun getData(): List<TodoItem> = _db.todoDao().selectAll()

    override fun getById(id: Long) = _db.todoDao().getById(id)

    override fun insert(todoItem: TodoItem) = _db.todoDao().insert(todoItem)

    override fun update(todoItem: TodoItem) = _db.todoDao().update(todoItem)
}