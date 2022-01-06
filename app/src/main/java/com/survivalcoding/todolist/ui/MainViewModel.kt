package com.survivalcoding.todolist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.survivalcoding.todolist.data.TodoRepository
import com.survivalcoding.todolist.data.model.Todo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val todoRepository: TodoRepository) : ViewModel() {

    val todos: LiveData<List<Todo>> = todoRepository.todos.asLiveData()

    var selectedTodo: Todo? = null

    fun upsertTodo(todo: Todo) {
        viewModelScope.launch {
            todoRepository.upsertTodo(todo)
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            todoRepository.deleteTodo(todo)
        }
    }
}