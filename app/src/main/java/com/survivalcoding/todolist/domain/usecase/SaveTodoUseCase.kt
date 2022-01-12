package com.survivalcoding.todolist.domain.usecase

import com.survivalcoding.todolist.domain.model.Todo

class SaveTodoUseCase(
    private val insertTodoUseCase: InsertTodoUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase,
) {
    suspend operator fun invoke(
        currentTodo: Todo?,
        title: String
    ) {
        if (currentTodo != null) {
            updateTodoUseCase(currentTodo.id!!, title)
        } else {
            insertTodoUseCase(title)
        }
    }
}