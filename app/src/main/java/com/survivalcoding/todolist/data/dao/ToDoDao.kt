package com.survivalcoding.todolist.data.dao

import androidx.room.*
import com.survivalcoding.todolist.data.dto.ToDoRoomDto

@Dao
interface ToDoDao {
    @Query("SELECT * FROM todoroomdto")
    suspend fun getAll(): List<ToDoRoomDto>

    @Query("SELECT * FROM todoroomdto WHERE LOWER(title) LIKE '%' || :query || '%'")
    suspend fun search(query: String): List<ToDoRoomDto>

    @Query("DELETE FROM todoroomdto WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(toDo: ToDoRoomDto)

    @Update
    suspend fun update(toDo: ToDoRoomDto)
}