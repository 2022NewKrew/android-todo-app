package com.survivalcoding.todolist.data

import com.survivalcoding.todolist.domain.entity.Todo
import com.survivalcoding.todolist.domain.repository.TodoRepository
import java.util.*

class TodoInMemoryRepositoryImpl : TodoRepository {
    private var _todos =
        (0L..30L).map {
            Todo(
                id = it,
                title = "$it 번째 일",
                timestamp = Date().time,
                isDone = false
            )
        }.toMutableList()

    override suspend fun getTodos() = _todos


    override suspend fun update(todo: Todo) {
        _todos.mapIndexed { idx, item ->
            if (item.id == todo.id) _todos[idx] = todo
        }
    }

    override suspend fun insert(todo: Todo) {
        _todos = _todos.plus(todo).toMutableList()
    }

    override suspend fun delete(todo: Todo) {
        _todos = _todos.filter { it.id != todo.id }.toMutableList()
    }


}