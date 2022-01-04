package com.survivalcoding.todolist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.survivalcoding.todolist.data.repository.TodoRepositoryImpl
import com.survivalcoding.todolist.domain.model.Todo

class MainViewModel : ViewModel() {
    private val repository = TodoRepositoryImpl()
    private val _todoList = MutableLiveData(repository.todoList)
    val todoList get() = _todoList
    private val _currentTodo = MutableLiveData<Todo?>()
    val currentTodo get() = _currentTodo

    fun updateIsDone(todo: Todo) {
        repository.updateItem(todo.copy(id = todo.id, title = todo.title, isDone = !todo.isDone))
        _todoList.value = repository.todoList
    }

    fun addItem(todo: Todo) {
        repository.addItem(todo)
        _todoList.value = repository.todoList
    }

    fun updateItem(todo: Todo) {
        repository.updateItem(todo)
        _todoList.value = repository.todoList
    }

    fun deleteItem(todo: Todo) {
        repository.deleteItem(todo)
        _todoList.value = repository.todoList
    }

    fun setTodo(todo: Todo?) {
        _currentTodo.value = todo
    }

    // currentTodo가 null인 경우 new task 만들기
    fun upsertItem(title: String) {
        var todo: Todo = currentTodo.value ?: newTodo()
        todo = todo.copy(id = todo.id, title = title, isDone = todo.isDone)

        if (currentTodo.value == null) addItem(todo)
        else updateItem(todo)
    }

    private fun newTodo(): Todo = Todo((todoList.value?.last()?.id ?: 0) + 1, "")
}