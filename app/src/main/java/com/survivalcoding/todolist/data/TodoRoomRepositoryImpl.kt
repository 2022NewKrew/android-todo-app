package com.survivalcoding.todolist.data


import com.survivalcoding.todolist.domain.entity.Todo
import com.survivalcoding.todolist.domain.repository.TodoRepository

class TodoRoomRepositoryImpl(db: TodoRoomDataBase) : TodoRepository {

    private val todoDao: TodoDao = db.todoDao()

    override suspend fun getTodos() = todoDao.getAll().map { TodoMapper.toEntity(it) }

    override suspend fun update(todo: Todo) {
        todoDao.updateTodo(TodoMapper.toModel(todo))
    }

    override suspend fun insert(todo: Todo) {
        todoDao.insertTodo(TodoMapper.toModel(todo))
    }

    override suspend fun delete(todo: Todo) {
        todoDao.delete(TodoMapper.toModel(todo))
    }

    // ++ diff util이 list의 참조 주소값만을 비교하는 것으로 판단되어 todos 전체를 바꾸는것으로 해야 한다.

}