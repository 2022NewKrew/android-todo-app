package com.survivalcoding.todolist.presentation.main.todolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.todolist.domain.model.ToDo
import com.survivalcoding.todolist.domain.usecase.DeleteToDoUseCase
import com.survivalcoding.todolist.domain.usecase.GetMatchingToDosUseCase
import com.survivalcoding.todolist.domain.usecase.UpdateToDoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoListViewModel @Inject constructor(
    private val deleteToDoUseCase: DeleteToDoUseCase,
    private val updateToDoUseCase: UpdateToDoUseCase,
    private val getMatchingToDosUseCase: GetMatchingToDosUseCase
) : ViewModel() {

    private val _toDoList = MutableStateFlow(getMatchingToDosUseCase(""))
    val toDoList = _toDoList.asStateFlow()

    private var searchJob: Job? = null

    private var toDoListOrder = OrderBy.TIME_ASC
        set(value) {
            field = value
//            _toDoList.value = sortToDoList(_toDoList.value)
        }

    fun setOrder(orderBy: OrderBy) {
        toDoListOrder = orderBy
    }

    private fun sortToDoList(toDoList: List<ToDo>): List<ToDo> {
        val sortedToDoList = mutableListOf<ToDo>()

        val notDoneToDoList = toDoList.filter {
            !it.isDone
        }
        sortedToDoList.addAll(sortToDoListBySelectedOrder(notDoneToDoList, toDoListOrder))

        val doneToDoList = toDoList.filter {
            it.isDone
        }
        sortedToDoList.addAll(sortToDoListBySelectedOrder(doneToDoList, toDoListOrder))

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

    fun changeDoneState(toDo: ToDo, isDone: Boolean) {
        updateToDoUseCase(toDo.id, toDo.copy(isDone = isDone))
    }

    fun deleteToDo(toDo: ToDo) {
        deleteToDoUseCase(toDo.id)
    }

    fun searchToDo(query: CharSequence?) {
        if (query == null) return

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(DEBOUNCE_TIME)
            if (isActive) {
                _toDoList.value = getMatchingToDosUseCase(query.toString())
            }
        }
    }


    companion object {
        private const val DEBOUNCE_TIME = 300L
    }
}

enum class OrderBy {
    TIME_ASC,
    TIME_DESC,
    TITLE_ASC,
    TITLE_DESC
}