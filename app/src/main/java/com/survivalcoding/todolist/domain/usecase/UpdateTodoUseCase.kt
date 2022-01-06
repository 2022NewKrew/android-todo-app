package com.survivalcoding.todolist.domain.usecase

import com.survivalcoding.todolist.domain.model.Todo
import com.survivalcoding.todolist.domain.repository.TodoRepository

class UpdateTodoUseCase(private val repository: TodoRepository) {
    suspend operator fun invoke(todo: Todo) {
        repository.updateItem(todo)
    }
}