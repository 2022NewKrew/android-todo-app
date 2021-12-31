package com.survivalcoding.todolist.presentation.add

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.survivalcoding.todolist.data.TodoRepository
import com.survivalcoding.todolist.model.Todo

//SavedStateHandle 사용하면 액티비티에서 set할 필요 없이 바로 intent에서 값을 여기에 저장시킨다.
class AddViewModel(handle: SavedStateHandle) : ViewModel() {
    private val todoRepository = TodoRepository
    var todo: Todo = Todo(id = (todoRepository.todos.size + 1).toLong())
    private var isModifying = false

    init{
        handle.get<Int>("modify")?.let {
            isModifying = true
            todo = todoRepository.todos[it]
        }
    }

    fun setDate(time: Long){
        todo = todo.copy(date = time)
    }

    fun updateTodo(title: String, date: Long, content: String){
        todo = todo.copy(title = title, date = date, content = content)
        if(isModifying) todoRepository.updateTodo(todo)
        else todoRepository.addTodo(todo)
    }
}