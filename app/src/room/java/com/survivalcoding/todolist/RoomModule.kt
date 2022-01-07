package com.survivalcoding.todolist

import com.survivalcoding.todolist.data.TodoDao
import com.survivalcoding.todolist.data.TodoRepository
import com.survivalcoding.todolist.data.TodoRoomRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    fun provideTodoRepository(todoDao: TodoDao): TodoRepository = TodoRoomRepository(todoDao)
}