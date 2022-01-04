package com.survivalcoding.todolist.data

import com.survivalcoding.todolist.domain.entity.Todo
import com.survivalcoding.todolist.domain.repository.TodoRepository

class TodoRepositoryImpl : TodoRepository {
    private var todos =
        (0..5).map { Todo(id = it.toLong(), title = "title $it") }.toMutableList()


    override fun getTodos(): List<Todo> = todos.sortedBy { it.timestamp }

    // ++ diff util이 list의 참조 주소값만을 비교하는 것으로 판단되어 todos 전체를 바꾸는것으로 해야 한다.
    override fun upDateIsDone(oldItem: Todo) {
        todos = todos.map {
            if (it.id == oldItem.id)
                oldItem.copy(isDone = !oldItem.isDone)
            else
                it

        }.toMutableList()
    }

    override fun insert(title: String) {
        todos.add(Todo(id = todos.size.toLong(), title = title))
    }

}