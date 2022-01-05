package com.survivalcoding.todolist.domain.usecase

import com.survivalcoding.todolist.domain.entity.Todo
import com.survivalcoding.todolist.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class GetTodosUseCase(private val todoRepository: TodoRepository) {
    operator fun invoke(): Flow<List<Todo>> {
        return todoRepository.getTodos()
    }
}