package com.survivalcoding.todolist.domain.usecase

import com.survivalcoding.todolist.domain.OrderBy
import com.survivalcoding.todolist.domain.model.ToDo
import javax.inject.Inject

class SortToDoListUseCase @Inject constructor() {

    operator fun invoke(toDoList: List<ToDo>, orderBy: OrderBy) = sortToDoList(toDoList, orderBy)

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