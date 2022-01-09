package com.survivalcoding.todolist.domain.usecase

import com.survivalcoding.todolist.domain.OrderBy
import com.survivalcoding.todolist.domain.model.ToDo
import com.survivalcoding.todolist.domain.repository.ToDoRepository
import javax.inject.Inject

class GetSortedMatchingToDosUseCase @Inject constructor(private val toDoRepository: ToDoRepository) {
    suspend operator fun invoke(query: String, orderBy: OrderBy): List<ToDo> {
        return when (orderBy) {
            OrderBy.TIME_ASC -> toDoRepository.getToDosOrderByTimeAsc(query)
            OrderBy.TIME_DESC -> toDoRepository.getToDosOrderByTimeDesc(query)
            OrderBy.TITLE_ASC -> toDoRepository.getToDosOrderByTitleAsc(query)
            OrderBy.TITLE_DESC -> toDoRepository.getToDosOrderByTitleDesc(query)
        }
    }
}