package com.survivalcoding.todolist.presentation.upsert

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.survivalcoding.todolist.domain.model.Todo
import com.survivalcoding.todolist.presentation.main.MainFragment.Companion.MODIFY

//SavedStateHandle 사용하면 bundle에서 값을 여기에 저장시킨다.
class UpsertViewModel(handle: SavedStateHandle) : ViewModel() {
    private var _todo = MutableLiveData(Todo())
    val todo: LiveData<Todo> get() = _todo
    private var editedFlag: Boolean = false

    private var upsertTodo: Todo = Todo()

    init {
        handle.get<Todo>(MODIFY)?.let {
            upsertTodo = it
            editedFlag = true
            _todo.value = upsertTodo
        }
    }

    fun getTodo(): Todo = upsertTodo

    fun isEditing(): Boolean = editedFlag

    fun updateDate(date: Long) {
        upsertTodo = upsertTodo.copy(dueDate = date)
        _todo.value = upsertTodo
    }

    fun updateTitle(title: String) {
        upsertTodo = upsertTodo.copy(title = title)
        _todo.value = upsertTodo
    }

    fun updateContent(content: String) {
        upsertTodo = upsertTodo.copy(content = content)
        _todo.value = upsertTodo
    }
}