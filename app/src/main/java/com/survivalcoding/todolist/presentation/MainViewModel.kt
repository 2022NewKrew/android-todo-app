package com.survivalcoding.todolist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.survivalcoding.todolist.data.repository.TodosRepositoryImpl
import com.survivalcoding.todolist.domain.model.Todo

// ViewModel: View로부터 독립적인 데이터 저장
// Activity가 완전히 종료할 때까지 데이터를 계속 가지고 있음
class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val todoListRepository = TodosRepositoryImpl(application)

    private var _todos = MutableLiveData(
        todoListRepository.getTodos()
    )
    val todos: LiveData<List<Todo>> = _todos

    fun getTodoByIndex(pos: Int): Todo = todoListRepository.getTodoByIndex(pos)

    fun toggleTodo(todo: Todo) { // 하나만 isDone을 변경
        todoListRepository.updateTodos(todo.copy(isDone = !todo.isDone))
        _todos.value = todoListRepository.getTodos()
    }

    fun updateTodo(todo: Todo) { // 업데이
        todoListRepository.updateTodos(todo)
        _todos.value = todoListRepository.getTodos()
    }

    fun addTodo(todo: Todo) { // 추가
        todoListRepository.addTodo(todo)
        _todos.value = todoListRepository.getTodos()
    }

    fun deleteTodo(todo: Todo) { // 삭제
        todoListRepository.deleteTodo(todo)
        _todos.value = todoListRepository.getTodos()
    }

    fun filterTodos(filter: String) { // 필터링 기능
        todoListRepository.filterTodos(filter)
        _todos.value = todoListRepository.getTodos()
    }
}