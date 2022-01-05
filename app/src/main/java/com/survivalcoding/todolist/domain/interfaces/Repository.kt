package com.survivalcoding.todolist.domain.interfaces

interface Repository<T, R> {
    fun insert(item: T): Boolean
    fun select(id: R): T?
    fun update(id: R, item: T): Boolean
    fun delete(id: R): Boolean
}