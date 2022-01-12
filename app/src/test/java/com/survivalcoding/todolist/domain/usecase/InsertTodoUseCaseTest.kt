package com.survivalcoding.todolist.domain.usecase

import com.survivalcoding.todolist.domain.model.Todo
import com.survivalcoding.todolist.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class InsertTodoUseCaseTest {
    private lateinit var useCase: InsertTodoUseCase
    private lateinit var todoRepository: TodoRepository

    @Before
    fun setUp() {
        todoRepository = TodoMockRepository()
        useCase = InsertTodoUseCase(todoRepository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun insertTest() = runBlocking {
        useCase("test")
        assertEquals("test", todoRepository.getTodos().first().first().title)

        useCase("test 2")
        assertEquals("test 2", todoRepository.getTodos().first()[1].title)
    }
}

class TodoMockRepository : TodoRepository {
    val _data = mutableListOf<Todo>()

    override fun getTodos(): Flow<List<Todo>> = flow {
        emit(_data)
    }

    override suspend fun getTodoById(id: Int): Todo? {
        TODO("Not yet implemented")
    }

    override suspend fun insert(todo: Todo) {
        _data.add(todo)
    }

    override suspend fun delete(todo: Todo) {
        TODO("Not yet implemented")
    }

    override suspend fun update(todo: Todo) {
        TODO("Not yet implemented")
    }

}