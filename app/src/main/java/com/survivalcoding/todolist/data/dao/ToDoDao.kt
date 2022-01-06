package com.survivalcoding.todolist.data.dao

import androidx.room.*
import com.survivalcoding.todolist.data.dto.ToDoRoomDto
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {
    @Query("SELECT * FROM todoroomdto")
    fun getAll(): Flow<List<ToDoRoomDto>>

    @Query("SELECT * FROM todoroomdto WHERE LOWER(title) LIKE '%' || :query || '%'")
    fun search(query: String): Flow<List<ToDoRoomDto>>

    @Query("DELETE FROM todoroomdto WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(toDo: ToDoRoomDto)

    @Update
    suspend fun update(toDo: ToDoRoomDto)
}