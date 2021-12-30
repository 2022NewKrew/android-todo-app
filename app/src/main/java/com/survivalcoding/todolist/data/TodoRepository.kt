package com.survivalcoding.todolist.data

import com.survivalcoding.todolist.domain.entity.Todo

class TodoRepository {
    var todos: List<Todo> =
        (0..30).map { Todo(id = it.toLong(), title = "title $it") }

    // todos가 변경되는 것보다
    // 개별 todo가 변경되는 것이 더 안전하다고 생각 -> 전체가 변경되지 않는다는 보장 & Todo가 널이 아니고 내부도 불변임을 보장
    //  diffUtil이 background에서 돌아감으로 전체를 업데이트 할 때 동시에 main thread에서 접근하여 오류가 발생하는 문제를
    // (거의)방지할 수 있지 않을까 생각
    // ++ diff util이 list의 참조 주소값만을 비교하는 것으로 판단되어 todos 전체를 바꾸는것으로 해야 한다.
    fun upDateIsDone(position: Int) {
        val isDone = todos[position].isDone
        todos = (0..30).map {
            if (it == position)
                Todo(id = it.toLong(), title = "title $it", isDone = !isDone)
            else
                todos[it]

        }

    }

}