package com.survivalcoding.todolist

import com.survivalcoding.todolist.data.TodoMemoryRepository
import com.survivalcoding.todolist.data.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    fun provideTodoRepository(): TodoRepository = TodoMemoryRepository()
}