package com.survivalcoding.todolist.data.datasource

import com.survivalcoding.todolist.domain.entity.Task

class TaskMockDataSource : TaskLocalDataSource {
    private var _tasks =
        (0L..30L).map {
            Task(
                id = it,
                taskName = "$it 번째 일",
                date = "2022-01-03",
                isDone = false
            )
        }.toMutableList()

    override val tasks: List<Task> = _tasks

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
        val tmpTasks = _tasks.plus(newTask).toMutableList()
        _tasks = tmpTasks
    }
}