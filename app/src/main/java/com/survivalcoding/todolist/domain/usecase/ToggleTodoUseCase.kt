package com.survivalcoding.todolist.domain.usecase

import androidx.lifecycle.viewModelScope
import com.survivalcoding.todolist.domain.model.Todo
import com.survivalcoding.todolist.domain.repository.TodoRepository
import kotlinx.coroutines.launch

class ToggleTodoUseCase(
    private val todoRepository: TodoRepository
) {
    suspend operator fun invoke(todo: Todo) {
        val newTodo = todo.copy(isDone = !todo.isDone)
        todoRepository.update(newTodo) // 오래 걸릴 수 있는 애
    }
}