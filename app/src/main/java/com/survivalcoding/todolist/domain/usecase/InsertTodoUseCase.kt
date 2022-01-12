package com.survivalcoding.todolist.domain.usecase

import com.survivalcoding.todolist.domain.model.Todo
import com.survivalcoding.todolist.domain.repository.TodoRepository

class InsertTodoUseCase(
    private val todoRepository: TodoRepository
) {
    suspend operator fun invoke(title: String) {
        todoRepository.insert(
            Todo(
                title = title
            )
        )
    }
}