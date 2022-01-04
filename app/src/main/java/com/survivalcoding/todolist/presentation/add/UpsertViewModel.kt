package com.survivalcoding.todolist.presentation.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.survivalcoding.todolist.data.UpsertRepository
import com.survivalcoding.todolist.data.TodoListRepository
import com.survivalcoding.todolist.domain.model.Todo
import com.survivalcoding.todolist.presentation.main.MainFragment.Companion.NEW
import com.survivalcoding.todolist.presentation.main.MainFragment.Companion.POSITION

//SavedStateHandle 사용하면 bundle에서 값을 여기에 저장시킨다.
class UpsertViewModel(handle: SavedStateHandle) : ViewModel() {
    private val upsertRepository = UpsertRepository()
    private var _todo = MutableLiveData(
        upsertRepository.getTodo()
    )
    val todo: LiveData<Todo> = _todo
    private var isModifying = false

    init {
        handle.get<Int>(POSITION)?.let {
            if (it != NEW) {
                isModifying = true
                upsertRepository.updateTodo(TodoListRepository.getTodoByIndex(it))
                _todo.value = upsertRepository.getTodo()
            }
        }
    }

    fun getTodo(): Todo = upsertRepository.getTodo()

    fun isEditing(): Boolean = isModifying

    fun updateDate(date: Long) {
        upsertRepository.updateDate(date)
        _todo.value = upsertRepository.getTodo()
    }

    fun updateTitle(title: String) {
        upsertRepository.updateTitle(title)
        _todo.value = upsertRepository.getTodo()
    }

    fun updateContent(content: String) {
        upsertRepository.updateContent(content)
        _todo.value = upsertRepository.getTodo()
    }
}