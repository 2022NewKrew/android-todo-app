package com.survivalcoding.todolist.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.survivalcoding.todolist.data.TodoRepositoryImpl
import com.survivalcoding.todolist.domain.entity.Todo
import com.survivalcoding.todolist.domain.usecase.GetTodosUseCase
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val todoRepositoryImpl = TodoRepositoryImpl(application.applicationContext)
    private val getTodosUseCase = GetTodosUseCase(todoRepositoryImpl)
    ///private var _todos: List<Todo>? = null
    val todos: LiveData<List<Todo>> = todoRepositoryImpl.getTodos().asLiveData()
    private val _todoNeedChanged = MutableLiveData<Todo?>(null)
    val todoNeedChanged get() = _todoNeedChanged


    fun toggleIsDone(todo: Todo) {
        todoRepositoryImpl.update(todo)
    }

    fun upsertTodo(title: String) {
        _todoNeedChanged.value?.let {
            todoRepositoryImpl.update(it.copy(title = title))
        } ?: todoRepositoryImpl.insert(Todo(title = title))
    }


}