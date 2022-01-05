package com.survivalcoding.todolist

import android.content.Context
import androidx.room.Room
import com.survivalcoding.todolist.data.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TodoAppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "todo_db"
    ).build()

    @Singleton
    @Provides
    fun provideTodoDao(db: AppDatabase) = db.todoDao()
}