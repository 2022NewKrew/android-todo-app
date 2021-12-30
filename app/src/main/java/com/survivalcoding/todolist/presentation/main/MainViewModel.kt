package com.survivalcoding.todolist.presentation.main

import androidx.lifecycle.ViewModel
import com.survivalcoding.todolist.data.TodoRepository
import com.survivalcoding.todolist.model.Todo

// ViewModel: View로부터 독립적인 데이터 저장
// Activity가 완전히 종료할 때까지 데이터를 계속 가지고 있음
class MainViewModel : ViewModel() {
    private val todoRepository = TodoRepository()

    val todos get() = todoRepository.todos // todos 데이터 갱신

    fun toggleTodo(todo: Todo) { // 하나만 업데이트
        todoRepository.updateTodos(todo)
    }

    fun setTodos(todos: List<Todo>) { // 전체 업데이트
        todoRepository.todos = todos
    }
}