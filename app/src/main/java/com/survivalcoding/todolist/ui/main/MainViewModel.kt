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
    var todoNeedChanged = MutableLiveData<Todo>(null)
    var isUpdate = MutableLiveData<Boolean>(false)


    fun toggleIsDone(item: Todo) {
        todoRepositoryImpl.upDateIsDone(item)
        _todos.value = todoRepositoryImpl.getTodos()
    }

    fun addTodo(title: String) {
        todoRepositoryImpl.insert(title)
        _todos.value = todoRepositoryImpl.getTodos()
    }

    fun updateTodo(title: String, id: Long) {
        if (id == -1L) return
        todoRepositoryImpl.upDateTitle(title, id)
        _todos.value = todoRepositoryImpl.getTodos()
    }

}