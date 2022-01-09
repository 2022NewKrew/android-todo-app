package com.survivalcoding.todolist.presentation

import android.app.Application
import androidx.lifecycle.*
import com.survivalcoding.todolist.data.repository.TodosLocalRepository
import com.survivalcoding.todolist.domain.model.Todo
import kotlinx.coroutines.launch

// ViewModel: View로부터 독립적인 데이터 저장
// Activity가 완전히 종료할 때까지 데이터를 계속 가지고 있음
class MainViewModel(
    application: Application,
    private val todosRepository: TodosLocalRepository
) : AndroidViewModel(application) {
    private var _todos = MutableLiveData<List<Todo>>()
    val todos: LiveData<List<Todo>> = _todos

    init {
        viewModelScope.launch {
            _todos.value = todosRepository.getTodos()
        }
    }

    suspend fun getTodoById(id: Int): Todo? = todosRepository.getTodoById(id)

    fun toggleTodo(todo: Todo) { // 하나만 isDone을 변경
        viewModelScope.launch {
            todosRepository.updateTodo(todo.copy(isDone = !todo.isDone))
            _todos.value = todosRepository.getTodos()
        }
    }

    fun updateTodo(todo: Todo) { // 업데이트
        viewModelScope.launch {
            todosRepository.updateTodo(todo)
            _todos.value = todosRepository.getTodos()
        }
    }

    fun addTodo(todo: Todo) { // 추가
        viewModelScope.launch {
            todosRepository.addTodo(todo)
            _todos.value = todosRepository.getTodos()
        }
    }

    fun deleteTodo(todo: Todo) { // 삭제
        viewModelScope.launch {
            todosRepository.deleteTodo(todo)
            _todos.value = todosRepository.getTodos()
        }
    }

    fun filterTodos(filter: String) { // 필터링 기능
        viewModelScope.launch {
            _todos.value = getFiltered(todosRepository.getTodos(), filter)
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

class MainViewModelFactory(
    private val application: Application,
    private val todosRepository: TodosLocalRepository
) : ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        // isAssignableFrom: modelClass가 MainViewModel로부터 상속되었는지 확인
        if (modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(
                application = application,
                todosRepository = todosRepository
            ) as T
        else throw IllegalArgumentException()
    }
}