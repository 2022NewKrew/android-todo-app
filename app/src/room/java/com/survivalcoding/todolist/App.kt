package com.survivalcoding.todolist

import android.app.Application
import androidx.room.Room
import com.survivalcoding.todolist.data.data_source.local.AppDatabase
import com.survivalcoding.todolist.data.repository.TodoRoomRepositoryImpl
import com.survivalcoding.todolist.domain.repository.TodoRepository
import com.survivalcoding.todolist.domain.usecase.*

class App : Application() {
    private val todoDao by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "todo-db"
        ).build().todoDao()
    }

    val todoRepository: TodoRepository by lazy {
        TodoRoomRepositoryImpl(todoDao)
    }

    val todoUseCases: TodoUseCases by lazy {
        TodoUseCases(
            saveTodoUseCase = SaveTodoUseCase(
                insertTodoUseCase = InsertTodoUseCase(todoRepository),
                updateTodoUseCase = UpdateTodoUseCase(todoRepository)
            ),
            deleteTodoUseCase = DeleteTodoUseCase(todoRepository),
            toggleTodoUseCase = ToggleTodoUseCase(todoRepository),
            getTodosUseCase = GetTodosUseCase(todoRepository),
        )
    }
}