package com.survivalcoding.todolist.domain.usecase

import com.survivalcoding.todolist.domain.model.ToDo
import com.survivalcoding.todolist.domain.repository.ToDoRepository
import javax.inject.Inject

class AddToDoUseCase @Inject constructor(private val toDoRepository: ToDoRepository) {
    suspend operator fun invoke(newItem: ToDo) {
        if (newItem.title.isNotBlank()) toDoRepository.addItem(newItem)
    }
}