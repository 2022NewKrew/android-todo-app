package com.survivalcoding.todolist.domain.usecase

import com.survivalcoding.todolist.domain.repository.ToDoRepository
import javax.inject.Inject

class SearchToDoUseCase @Inject constructor(private val toDoRepository: ToDoRepository) {
    operator fun invoke(query: String) {
        toDoRepository.searchItem(query)
    }
}