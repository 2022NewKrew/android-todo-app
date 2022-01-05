package com.survivalcoding.todolist.domain.interfaces

import com.survivalcoding.todolist.domain.models.TodoItem

interface TodoRepository : Repository<TodoItem, Long> {
    override fun insert(item: TodoItem): Boolean
    override fun select(id: Long): TodoItem?
    override fun update(id: Long, item: TodoItem): Boolean
    override fun delete(id: Long): Boolean
    fun clear()
}