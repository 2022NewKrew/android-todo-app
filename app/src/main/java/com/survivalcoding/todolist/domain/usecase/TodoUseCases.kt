package com.survivalcoding.todolist.domain.usecase

data class TodoUseCases(
    val saveTodoUseCase: SaveTodoUseCase,
    val deleteTodoUseCase: DeleteTodoUseCase,
    val toggleTodoUseCase: ToggleTodoUseCase,
    val getTodosUseCase: GetTodosUseCase,
)