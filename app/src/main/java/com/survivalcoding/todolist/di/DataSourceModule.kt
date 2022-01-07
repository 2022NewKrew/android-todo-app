package com.survivalcoding.todolist.di

import com.survivalcoding.todolist.data.datasource.ToDoLocalDataSource
import com.survivalcoding.todolist.data.datasource.ToDoRoomDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindsToDoLocalDataSource(toDoRoomDataSource: ToDoRoomDataSource): ToDoLocalDataSource
}