package com.survivalcoding.todolist.data

import com.survivalcoding.todolist.domain.model.Todo

// AddActivity에 대한 레포지토리를 만들었는데 좋은 방법인진 잘 모르겠음
class UpsertRepository {
    private var todo = Todo(id = (TodoListRepository.getTodos().maxOf { it.id } + 1))

    fun getTodo(): Todo = todo

    fun updateDate(date: Long) {
        todo = todo.copy(date = date)
    }

    fun updateContent(content: String) {
        todo = todo.copy(content = content)
    }

    fun updateTitle(title: String) {
        todo = todo.copy(title = title)
    }

    fun updateTodo(todo: Todo) {
        this.todo = todo
    }
}