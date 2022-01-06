package com.survivalcoding.todolist.ui.main

import androidx.lifecycle.*
import com.survivalcoding.todolist.domain.entity.Todo
import com.survivalcoding.todolist.domain.repository.TodoRepository
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
    fun searchTodos(name: String?) : List<Todo>?{
        if (name.isNullOrEmpty()) return todos.value

        return todos.value?.filter{
            name.lowercase() in it.title.lowercase()
        }
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