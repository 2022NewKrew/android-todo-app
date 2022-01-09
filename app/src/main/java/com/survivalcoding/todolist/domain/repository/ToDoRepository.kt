package com.survivalcoding.todolist.domain.repository

import com.survivalcoding.todolist.domain.model.ToDo

interface ToDoRepository {
    suspend fun updateToDo(id: Long, newItem: ToDo)
    suspend fun deleteToDo(id: Long)
    suspend fun addToDo(newItem: ToDo)
    suspend fun getAllToDo(): List<ToDo>
    suspend fun getMatchingToDos(query: String): List<ToDo>
    suspend fun getToDosOrderByTimeAsc(query: String): List<ToDo>
    suspend fun getToDosOrderByTimeDesc(query: String): List<ToDo>
    suspend fun getToDosOrderByTitleAsc(query: String): List<ToDo>
    suspend fun getToDosOrderByTitleDesc(query: String): List<ToDo>
}