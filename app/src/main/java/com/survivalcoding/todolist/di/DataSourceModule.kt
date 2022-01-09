package com.survivalcoding.todolist.di

import com.survivalcoding.todolist.BuildConfig
import com.survivalcoding.todolist.data.datasource.ToDoInMemoryDataSource
import com.survivalcoding.todolist.data.datasource.ToDoLocalDataSource
import com.survivalcoding.todolist.data.datasource.ToDoRoomDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    fun providesToDoLocalDataSource(
        toDoRoomDataSource: ToDoRoomDataSource,
        toDoInMemoryDataSource: ToDoInMemoryDataSource
    ): ToDoLocalDataSource = if (BuildConfig.FLAVOR == "room") {
        toDoRoomDataSource
    } else {
        toDoInMemoryDataSource
    }
}