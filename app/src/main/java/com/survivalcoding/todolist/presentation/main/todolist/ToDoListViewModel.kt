package com.survivalcoding.todolist.presentation.main.todolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.todolist.domain.model.ToDo
import com.survivalcoding.todolist.domain.usecase.DeleteToDoUseCase
import com.survivalcoding.todolist.domain.usecase.GetAllToDoUseCase
import com.survivalcoding.todolist.domain.usecase.SearchToDoUseCase
import com.survivalcoding.todolist.domain.usecase.UpdateToDoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoListViewModel @Inject constructor(
    private val deleteToDoUseCase: DeleteToDoUseCase,
    private val updateToDoUseCase: UpdateToDoUseCase,
    private val getAllToDoUseCase: GetAllToDoUseCase,
    private val searchToDoUseCase: SearchToDoUseCase
) : ViewModel() {

    private val _toDoList = MutableStateFlow<List<ToDo>>(listOf())
    val toDoList = _toDoList.asStateFlow()

    private var searchJob: Job? = null

    private var toDoListOrder = OrderBy.TIME_ASC
        set(value) {
            field = value
            _toDoList.value = sortToDoListBy(_toDoList.value, value)
        }

    init {
        viewModelScope.launch {
            getAllToDoUseCase().collectLatest { toDoList ->
                _toDoList.value = sortToDoListBy(toDoList, toDoListOrder)
            }
        }
    }

    fun setOrder(orderBy: OrderBy) {
        toDoListOrder = orderBy
    }

    fun changeDoneState(toDo: ToDo, isDone: Boolean) {
        updateToDoUseCase(toDo.id, toDo.copy(isDone = isDone))
    }

    fun deleteToDo(toDo: ToDo) {
        deleteToDoUseCase(toDo.id)
    }

    private fun sortToDoListBy(toDoList: List<ToDo>, orderBy: OrderBy): List<ToDo> {
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

    fun searchToDo(query: CharSequence?) {
        if (query == null) return

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(300L)
            if (isActive) {
                searchToDoUseCase(query.toString())
            }
        }
    }
}

enum class OrderBy {
    TIME_ASC,
    TIME_DESC,
    TITLE_ASC,
    TITLE_DESC
}