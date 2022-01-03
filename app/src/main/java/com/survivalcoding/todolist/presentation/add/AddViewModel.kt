package com.survivalcoding.todolist.presentation.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.survivalcoding.todolist.data.AddRepository
import com.survivalcoding.todolist.data.TodoListRepository
import com.survivalcoding.todolist.domain.model.Todo

//SavedStateHandle 사용하면 액티비티에서 set할 필요 없이 바로 intent에서 값을 여기에 저장시킨다.
class AddViewModel(handle: SavedStateHandle) : ViewModel() {
    private val addTodoRepository = AddRepository()
    private var _todo = MutableLiveData(
        addTodoRepository.getTodo()
    )
    val todo: LiveData<Todo> = _todo

    init {
        handle.get<Int>("modify")?.let {
            addTodoRepository.isModifyingTrue()
            addTodoRepository.updateTodo(TodoListRepository.getTodoByIndex(it))
            _todo.value = addTodoRepository.getTodo()
        }
    }

    fun getTodo(): Todo = addTodoRepository.getTodo()
    fun getIsModifying(): Boolean = addTodoRepository.getIsModifying()

    fun updateDate(date: Long) {
        addTodoRepository.updateDate(date)
        _todo.value = addTodoRepository.getTodo()
    }

    fun updateTitle(title: String) {
        addTodoRepository.updateTitle(title)
        _todo.value = addTodoRepository.getTodo()
    }

    fun updateContent(content: String) {
        addTodoRepository.updateContent(content)
        _todo.value = addTodoRepository.getTodo()
    }
}