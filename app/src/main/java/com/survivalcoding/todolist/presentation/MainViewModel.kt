package com.survivalcoding.todolist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.survivalcoding.todolist.data.database.AppDatabase
import com.survivalcoding.todolist.data.repository.TodosRoomRepository
import com.survivalcoding.todolist.domain.model.Todo
import kotlinx.coroutines.launch

// ViewModel: View로부터 독립적인 데이터 저장
// Activity가 완전히 종료할 때까지 데이터를 계속 가지고 있음
class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val todoListRepository = TodosRoomRepository(
        Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java,
            "database"
        )
            .allowMainThreadQueries()
            .build().todoDao()
    )

    private var _todos = MutableLiveData<List<Todo>>()
    val todos: LiveData<List<Todo>> = _todos

    init {
        viewModelScope.launch {
            _todos.value = todoListRepository.getTodos()
        }
    }

    suspend fun getTodoById(id: Int): Todo = todoListRepository.getTodoById(id)

    fun toggleTodo(todo: Todo) { // 하나만 isDone을 변경
        viewModelScope.launch {
            todoListRepository.updateTodo(todo.copy(isDone = !todo.isDone))
            _todos.value = todoListRepository.getTodos()
        }
    }

    fun updateTodo(todo: Todo) { // 업데이트
        viewModelScope.launch {
            todoListRepository.updateTodo(todo)
            _todos.value = todoListRepository.getTodos()
        }
    }

    fun addTodo(todo: Todo) { // 추가
        viewModelScope.launch {
            todoListRepository.addTodo(todo)
            _todos.value = todoListRepository.getTodos()
        }
    }

    fun deleteTodo(todo: Todo) { // 삭제
        viewModelScope.launch {
            todoListRepository.deleteTodo(todo)
            _todos.value = todoListRepository.getTodos()
        }
    }

    fun filterTodos(filter: String) { // 필터링 기능
        viewModelScope.launch {
            _todos.value = getFiltered(todoListRepository.getTodos(), filter)
        }
    }

    private fun getFiltered(
        todos: List<Todo>,
        filter: String
    ): List<Todo> { // 정렬을 isDone, dueDate, createDate(내림차순)으로 진행
        return todos.filter {
            it.title.contains(filter, ignoreCase = true)
        }
    }
}