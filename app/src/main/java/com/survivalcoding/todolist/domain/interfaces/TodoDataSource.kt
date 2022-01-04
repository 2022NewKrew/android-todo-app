package com.survivalcoding.todolist.domain.interfaces

interface TodoDataSource<T> {
    fun getData(): List<T>
}