package com.survivalcoding.todolist.data

import com.survivalcoding.todolist.model.Task

object TaskRepository {
    var tasks = listOf<Task>()
//    ArrayList<Task>().apply
//    {
//        (1..30).forEach {
//            this.add(
//                Task(
//                    id = it.toLong(),
//                    taskName = "$it 번째 일",
//                    date = "2021-12-$it",
//                    isDone = false,
//                )
//            )
//        }
//    }.toList()

    fun switchTaskProgressed(id: Long) {
        tasks = tasks.toMutableList().map {
            if (it.id == id) it.copy(isDone = !it.isDone)
            else it
        }
    }

    fun insert(newTask: Task) {
        tasks = tasks.toMutableList().apply { add(0, newTask) }
    }
}