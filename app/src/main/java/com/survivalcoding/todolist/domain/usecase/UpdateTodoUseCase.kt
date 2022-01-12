package com.survivalcoding.todolist.domain.usecase

import com.survivalcoding.todolist.domain.model.Todo
import com.survivalcoding.todolist.domain.repository.TodoRepository
import java.util.*

class UpdateTodoUseCase(
    private val todoRepository: TodoRepository
) {
    suspend operator fun invoke(id: Int, title: String) {
        todoRepository.update(
            Todo(
                id = id,
                title = title,
                timestamp = Date().time
            )
        )
    }
}