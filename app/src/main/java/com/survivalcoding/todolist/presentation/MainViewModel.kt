package com.survivalcoding.todolist.presentation

import android.app.Application
import androidx.lifecycle.*
import com.survivalcoding.todolist.domain.model.Todo
import com.survivalcoding.todolist.domain.repository.TodoRepository
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel(
    application: Application,
    private val todoRepository: TodoRepository,
) : AndroidViewModel(application) {
    var currentTodo: Todo? = null

//    private val todoRepository = TodoInMemoryRepository()
//    private val todoRepository: TodoRepository = TodoRoomRepository(
//        Room.databaseBuilder(
//            application.applicationContext,
//            AppDatabase::class.java, "todo-db"
//        ).build().todoDao()
//    )

    private val _todos = MutableLiveData<List<Todo>>()
    val todos: LiveData<List<Todo>> = _todos

    init {
        viewModelScope.launch {
            _todos.value = todoRepository.getTodos()
        }
    }

    fun toggleTodo(todo: Todo) {
        val newTodo = todo.copy(isDone = !todo.isDone)

        viewModelScope.launch {
            todoRepository.update(newTodo) // 오래 걸릴 수 있는 애
            _todos.value = todoRepository.getTodos()
        }
    }

    fun saveTodo(title: String) {
        viewModelScope.launch {
            if (currentTodo != null) {
                todoRepository.update(
                    Todo(
                        id = currentTodo!!.id,
                        title = title,
                        timestamp = Date().time
                    )
                )
            } else {
                todoRepository.insert(
                    Todo(
                        title = title
                    )
                )
            }
            _todos.value = todoRepository.getTodos()
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            todoRepository.delete(todo)
            _todos.value = todoRepository.getTodos()
        }
    }

    class MainViewModelFactory(
        private val application: Application,
        private val todoRepository: TodoRepository
    ) :
        ViewModelProvider.AndroidViewModelFactory(
            application
        ) {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java))
                return MainViewModel(
                    application = application,
                    todoRepository = todoRepository
                ) as T
            else throw IllegalArgumentException()
        }
    }
}