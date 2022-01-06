package com.survivalcoding.todolist.domain.usecase

import com.survivalcoding.todolist.domain.model.Todo
import com.survivalcoding.todolist.domain.repository.TodoRepository

class SearchTodoUseCase(private val repository: TodoRepository) {
    suspend operator fun invoke(query: String): List<Todo> = repository.search(query)
}