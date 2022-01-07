package com.survivalcoding.todolist.domain.usecase

import com.survivalcoding.todolist.domain.OrderBy
import com.survivalcoding.todolist.domain.model.ToDo
import com.survivalcoding.todolist.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMatchingToDosUseCase @Inject constructor(private val toDoRepository: ToDoRepository) {

    operator fun invoke(query: String, orderBy: OrderBy): Flow<List<ToDo>> {
        return toDoRepository.getMatchingItem(query).flatMapLatest {
            flow {
                emit(sortToDoList(it, orderBy))
            }
        }
    }

    private fun sortToDoList(toDoList: List<ToDo>, orderBy: OrderBy): List<ToDo> {
        val sortedToDoList = mutableListOf<ToDo>()

        val notDoneToDoList = toDoList.filter {
            !it.isDone
        }
        sortedToDoList.addAll(sortToDoListBySelectedOrder(notDoneToDoList, orderBy))

        val doneToDoList = toDoList.filter {
            it.isDone
        }
        sortedToDoList.addAll(sortToDoListBySelectedOrder(doneToDoList, orderBy))

        return sortedToDoList
    }

    private fun sortToDoListBySelectedOrder(toDoList: List<ToDo>, orderBy: OrderBy): List<ToDo> {
        return when (orderBy) {
            OrderBy.TIME_ASC -> toDoList.sortedBy { toDo ->
                toDo.timeStamp
            }
            OrderBy.TIME_DESC -> toDoList.sortedByDescending { toDo ->
                toDo.timeStamp
            }
            OrderBy.TITLE_ASC -> toDoList.sortedBy { toDo ->
                toDo.title
            }
            OrderBy.TITLE_DESC -> toDoList.sortedByDescending { toDo ->
                toDo.title
            }
        }
    }
}