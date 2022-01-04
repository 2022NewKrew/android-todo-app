package com.survivalcoding.todolist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.survivalcoding.todolist.data.TodoListRepository
import com.survivalcoding.todolist.domain.model.Todo

// ViewModel: View로부터 독립적인 데이터 저장
// Activity가 완전히 종료할 때까지 데이터를 계속 가지고 있음
class MainViewModel : ViewModel() {
    private val todoListRepository = TodoListRepository()

    private var _todos = MutableLiveData(
        todoListRepository.getTodos()
    )
    val todos: LiveData<List<Todo>> = _todos

    fun getTodoByIndex(pos: Int): Todo = todoListRepository.getTodoByIndex(pos)

    fun toggleTodo(todo: Todo) { // 하나만 isDone을 변경
        todoListRepository.updateTodo(todo.copy(isDone = !todo.isDone))
        _todos.value = todoListRepository.getTodos()
    }

    fun updateTodo(todo: Todo) { // 하나만 업데이트
        todoListRepository.updateTodo(todo)
        _todos.value = todoListRepository.getTodos()
    }

    fun addTodo(todo: Todo) { // 하나만 업데이트
        todoListRepository.addTodo(todo)
        _todos.value = todoListRepository.getTodos()
    }

    fun deleteTodo(todo: Int) { // 하나만 업데이트
        todoListRepository.deleteTodo(todo)
        _todos.value = todoListRepository.getTodos()
    }
}