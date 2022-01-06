package com.survivalcoding.todolist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.survivalcoding.todolist.domain.model.Todo
import com.survivalcoding.todolist.domain.repository.TodoRepository
import com.survivalcoding.todolist.domain.usecase.*
import kotlinx.coroutines.launch

class MainViewModel(
    private val getTodoListUseCase: GetTodoListUseCase,
    private val insertTodoUseCase: InsertTodoUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase,
    private val searchTodoUseCase: SearchTodoUseCase
) : ViewModel() {
    private val _todoList = MutableLiveData<List<Todo>>()
    val todoList get() = _todoList
    private val _currentTodo = MutableLiveData<Todo?>()
    val currentTodo get() = _currentTodo

    fun getTodoList() {
        viewModelScope.launch {
            _todoList.postValue(getTodoListUseCase())
        }
    }

    fun updateIsDone(todo: Todo) {
        viewModelScope.launch {
            updateTodoUseCase(todo.copy(isDone = !todo.isDone))
            _todoList.postValue(getTodoListUseCase())
        }
    }

    private fun addItem(todo: Todo) {
        viewModelScope.launch {
            insertTodoUseCase(todo)
        }
    }

    private fun updateItem(todo: Todo) {
        viewModelScope.launch {
            updateTodoUseCase(todo)
        }
    }

    fun deleteItem(todo: Todo) {
        viewModelScope.launch {
            deleteTodoUseCase(todo)
        }
    }

    fun setTodo(todo: Todo?) {
        _currentTodo.value = todo
    }

    // currentTodo가 null인 경우 new task 만들기
    fun upsertItem(title: String) {
        var todo: Todo = currentTodo.value ?: newTodo()
        todo = todo.copy(title = title)

        if (currentTodo.value == null) addItem(todo)
        else updateItem(todo)
    }

    private fun newTodo(): Todo = Todo(null, "")

    fun search(query: String) {
        viewModelScope.launch {
            _todoList.postValue(searchTodoUseCase(query))
        }
    }
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private val repository: TodoRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(
                GetTodoListUseCase(repository),
                InsertTodoUseCase(repository),
                UpdateTodoUseCase(repository),
                DeleteTodoUseCase(repository),
                SearchTodoUseCase(repository)
            ) as T
        else throw IllegalArgumentException()
    }
}