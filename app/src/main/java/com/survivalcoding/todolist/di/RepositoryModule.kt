package com.survivalcoding.todolist.di

import com.survivalcoding.todolist.data.repository.ToDoRepositoryImpl
import com.survivalcoding.todolist.domain.repository.ToDoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsToDoRepository(toDoRepositoryImpl: ToDoRepositoryImpl): ToDoRepository
}