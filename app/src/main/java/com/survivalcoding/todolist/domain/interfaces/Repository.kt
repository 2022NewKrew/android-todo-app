package com.survivalcoding.todolist.domain.interfaces

interface Repository<T, R> {
    suspend fun insert(item: T): Boolean
    suspend fun select(id: R): T?
    suspend fun update(id: R, item: T): Boolean
    suspend fun delete(id: R): Boolean
}