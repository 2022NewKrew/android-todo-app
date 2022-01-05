package com.survivalcoding.todolist.domain.entity

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Task(
    @PrimaryKey val id: Long,
    val taskName: String,
    val taskInfo: String,
    val date: Long,
    val isDone: Boolean = false,
    val isExpanded: Boolean = false,
) : Parcelable

@Dao
interface TaskDao {
    @Query("SELECT * FROM task ORDER BY isDone, date DESC")
    fun getAllLive(): LiveData<List<Task>>

    @Query("SELECT * FROM task ORDER BY isDone, date DESC")
    fun getAllList(): List<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg tasks: Task)

    @Delete
    fun delete(task: Task)
}

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
