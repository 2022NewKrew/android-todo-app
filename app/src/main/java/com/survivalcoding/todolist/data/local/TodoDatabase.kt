package com.survivalcoding.todolist.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.survivalcoding.todolist.domain.model.Todo

@Database(entities = [Todo::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        private var instance: TodoDatabase? = null

        fun getDatabase(context: Context): TodoDatabase {
            return instance ?: synchronized(this) {
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    "TodoDatabase"
                ).build()
                this.instance = db

                // return instance
                db
            }
        }
    }
}