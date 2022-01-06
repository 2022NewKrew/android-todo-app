package com.survivalcoding.todolist.domain.usecase

import com.survivalcoding.todolist.domain.repository.ToDoRepository
import javax.inject.Inject

class GetMatchingToDosUseCase @Inject constructor(private val toDoRepository: ToDoRepository) {
    operator fun invoke(query: String) = toDoRepository.getMatchingItem(query)
}