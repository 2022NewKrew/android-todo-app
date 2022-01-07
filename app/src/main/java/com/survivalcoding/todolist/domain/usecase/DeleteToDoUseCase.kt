package com.survivalcoding.todolist.domain.usecase

import com.survivalcoding.todolist.domain.repository.ToDoRepository
import javax.inject.Inject

class DeleteToDoUseCase @Inject constructor(private val toDoRepository: ToDoRepository) {
    suspend operator fun invoke(id: Long) = toDoRepository.deleteItem(id)
}