package com.survivalcoding.todolist.domain.usecase

import com.survivalcoding.todolist.domain.model.ToDo
import com.survivalcoding.todolist.domain.repository.ToDoRepository
import javax.inject.Inject

class UpdateToDoUseCase @Inject constructor(private val toDoRepository: ToDoRepository) {
    suspend operator fun invoke(id: Long, newItem: ToDo) = toDoRepository.updateItem(id, newItem)
}