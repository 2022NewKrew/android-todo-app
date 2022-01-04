package com.survivalcoding.todolist.data.datasource

import com.survivalcoding.todolist.domain.entity.Task

class TaskInMemoryDataSource : TaskLocalDataSource {
    private var _tasks = mutableListOf<Task>()

    override val tasks: List<Task> get() = _tasks

    override fun updateTask(id: Long) {
        _tasks = _tasks.map { task ->
            if (task.id == id) task.copy(isDone = !task.isDone) else task
        }.toMutableList()
    }

    override fun deleteTask(id: Long) {
        _tasks = _tasks.filter { task ->
            task.id != id
        }.toMutableList()
    }

    override fun insertTask(newTask: Task) {
        val tmpTasks = _tasks.toMutableList().apply { add(0, newTask) }
        _tasks = tmpTasks
    }
}