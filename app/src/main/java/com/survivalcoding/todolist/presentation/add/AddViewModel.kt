package com.survivalcoding.todolist.presentation.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.survivalcoding.todolist.data.repository.TodoRepositoryImpl
import com.survivalcoding.todolist.model.Todo

class AddViewModel : ViewModel() {
    private val repository = TodoRepositoryImpl()
    private val _todoList = MutableLiveData(repository.todoList)
    val todoList get() = _todoList
    private val _todo = MutableLiveData<Todo>()
    val todo get() = _todo

    fun setTodo(todo: Todo?) {
        _todo.value = todo ?: newTodo()
    }

    fun getUpdateTodo(title: String): Todo = todo.value?.copy(
        id = todo.value?.id ?: 1,
        title = title,
        isDone = todo.value?.isDone ?: false
    ) ?: newTodo()

    private fun newTodo(): Todo = Todo((todoList.value?.last()?.id ?: 0) + 1, "")
}