package com.survivalcoding.todolist.data.datasource

import com.survivalcoding.todolist.domain.OrderBy
import com.survivalcoding.todolist.domain.model.ToDo
import com.survivalcoding.todolist.domain.usecase.SortToDoListUseCase
import java.util.concurrent.atomic.AtomicLong
import javax.inject.Inject

class ToDoInMemoryDataSource @Inject constructor(private val sortToDoListUseCase: SortToDoListUseCase) : ToDoLocalDataSource {

    private var nextId = AtomicLong(1)

    private var toDoList: List<ToDo> = listOf()

    override suspend fun updateToDo(id: Long, newItem: ToDo) {
        toDoList = toDoList.map {
            if (it.id == id) {
                newItem
            } else {
                it
            }
        }
    }

    override suspend fun deleteToDo(id: Long) {
        toDoList = toDoList.filter {
            it.id != id
        }
    }

    override suspend fun addToDo(newItem: ToDo) {
        toDoList = toDoList.plus(newItem.copy(id = nextId.getAndIncrement()))
    }

    override suspend fun getAllToDo() = toDoList

    override suspend fun getMatchingToDos(query: String) =
        toDoList.filter {
            it.title
                .lowercase()
                .contains(query.lowercase())
        }

    override suspend fun getToDosOrderByTimeAsc(query: String) = sortToDoListUseCase(getMatchingToDos(query), OrderBy.TIME_ASC)

    override suspend fun getToDosOrderByTimeDesc(query: String) = sortToDoListUseCase(getMatchingToDos(query), OrderBy.TIME_DESC)

    override suspend fun getToDosOrderByTitleAsc(query: String) = sortToDoListUseCase(getMatchingToDos(query), OrderBy.TITLE_ASC)

    override suspend fun getToDosOrderByTitleDesc(query: String) = sortToDoListUseCase(getMatchingToDos(query), OrderBy.TITLE_DESC)
}