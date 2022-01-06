package com.survivalcoding.todolist.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.room.Room
import com.survivalcoding.todolist.data.TodoRepositoryImpl
import com.survivalcoding.todolist.data.TodoRoomDataBase
import com.survivalcoding.todolist.domain.entity.Todo
import com.survivalcoding.todolist.domain.repository.TodoRepository
import com.survivalcoding.todolist.domain.usecase.GetTodosUseCase
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import java.util.*

class MainViewModel(private val todoRepositoryImpl: TodoRepository) : ViewModel() {

    val todos: LiveData<List<Todo>> = todoRepositoryImpl.getTodos().asLiveData()
    private val _todoNeedChanged = MutableLiveData<Todo?>(null)
    val todoNeedChanged get() = _todoNeedChanged


    fun toggleIsDone(todo: Todo) {
        todoRepositoryImpl.update(todo)
    }

    fun upsertTodo(title: String) {
        _todoNeedChanged.value?.let {
            todoRepositoryImpl.update(it.copy(title = title, timestamp = Date().time))
        } ?: todoRepositoryImpl.insert(Todo(title = title))
    }

    fun removeTodo(todo: Todo) {
        todoRepositoryImpl.delete(todo)
    }
}

class MainViewModelFactory(private val repository: TodoRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(repository) as T
        else
            throw IllegalArgumentException()
    }
}