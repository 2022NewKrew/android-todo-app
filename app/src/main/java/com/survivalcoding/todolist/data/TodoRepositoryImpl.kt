package com.survivalcoding.todolist.data


import com.survivalcoding.todolist.domain.entity.Todo
import com.survivalcoding.todolist.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TodoRepositoryImpl(db: TodoRoomDataBase) : TodoRepository {

    private val todoDao: TodoDao = db.todoDao()

    override fun getTodos(): Flow<List<Todo>> =
        todoDao.getAll().map { todoModels -> todoModels.map { TodoMapper.toEntity(it) } }



    override fun update(todo: Todo) {
        todoDao.updateTodo(TodoMapper.toModel(todo))
    }

    override fun insert(todo: Todo) {
        todoDao.insertTodo(TodoMapper.toModel(todo))
    }

    override fun delete(todo: Todo) {
        todoDao.delete(TodoMapper.toModel(todo))
    }

    // ++ diff util이 list의 참조 주소값만을 비교하는 것으로 판단되어 todos 전체를 바꾸는것으로 해야 한다.

}