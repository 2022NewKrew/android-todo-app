package com.survivalcoding.todolist.presentation.main.createtodo

import com.survivalcoding.todolist.domain.model.ToDo

data class CreateToDoEvent(
    val editedFlag: Boolean = false,
    val toDo: ToDo
)