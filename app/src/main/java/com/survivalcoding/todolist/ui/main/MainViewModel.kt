package com.survivalcoding.todolist.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.survivalcoding.todolist.data.TodoRepositoryImpl
import com.survivalcoding.todolist.domain.entity.Todo
import com.survivalcoding.todolist.domain.usecase.GetTodosUseCase

class MainViewModel : ViewModel() {

    private val todoRepositoryImpl = TodoRepositoryImpl()
    private val getTodosUseCase = GetTodosUseCase(todoRepositoryImpl)
    private val _todos = MutableLiveData(getTodosUseCase())
    val todos: LiveData<List<Todo>> = _todos
    private val _todoNeedChanged = MutableLiveData<Todo?>(null)
    val todoNeedChanged get() = _todoNeedChanged

    fun toggleIsDone(item: Todo) {
        todoRepositoryImpl.upDateIsDone(item)
        _todos.value = todoRepositoryImpl.getTodos()
    }

    fun upsertTodo(title: String) {
        _todoNeedChanged.value?.let {
            todoRepositoryImpl.upDateTitle(title, it.id)
        } ?: todoRepositoryImpl.insert(title)

        _todos.value = todoRepositoryImpl.getTodos()
    }


}