package com.survivalcoding.todolist.domain.usecase

import com.survivalcoding.todolist.domain.entity.Todo
import com.survivalcoding.todolist.domain.repository.TodoRepository

class GetTodosUseCase(private val todoRepository: TodoRepository) {
    suspend operator fun invoke(): List<Todo> {
        return todoRepository.getTodos()
    }
}