package com.survivalcoding.todolist.data

import com.survivalcoding.todolist.domain.entity.Todo

object TodoMapper {

    fun toEntity(model: TodoModel): Todo {
        return with(model) {
            Todo(id, title, timestamp, isDone)
        }
    }

    fun toModel(entity: Todo): TodoModel {
        return with(entity) {
            TodoModel(id, title, timestamp, isDone)
        }
    }
}