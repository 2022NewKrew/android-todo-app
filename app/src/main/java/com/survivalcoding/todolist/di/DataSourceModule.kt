package com.survivalcoding.todolist.di

import com.survivalcoding.todolist.BuildConfig
import com.survivalcoding.todolist.data.datasource.ToDoInMemoryDataSource
import com.survivalcoding.todolist.data.datasource.ToDoLocalDataSource
import com.survivalcoding.todolist.data.datasource.ToDoRoomDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DataSourceModule {

    @Provides
    fun providesToDoLocalDataSource(
        toDoRoomDataSource: ToDoRoomDataSource,
        toDoInMemoryDataSource: ToDoInMemoryDataSource
    ): ToDoLocalDataSource = if (BuildConfig.FLAVOR == "room") {
        toDoRoomDataSource
    } else {
        toDoInMemoryDataSource
    }
}