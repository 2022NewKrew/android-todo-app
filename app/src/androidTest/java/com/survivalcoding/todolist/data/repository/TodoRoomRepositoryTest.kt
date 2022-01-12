package com.survivalcoding.todolist.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.survivalcoding.todolist.data.data_source.local.AppDatabase
import com.survivalcoding.todolist.domain.model.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@RunWith(AndroidJUnit4::class)
class TodoRoomRepositoryTest {

    private lateinit var repository: TodoRoomRepository
    private lateinit var db: AppDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).build()

        repository = TodoRoomRepository(db.todoDao())
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        db.close()
    }

    // Room, Flow, Coroutines
    @Test
    fun insertTest() = runBlocking {
        val todo = Todo(
            id = 1,
            title = "test"
        )

        repository.insert(todo)

        val todos = repository.getTodos().first()
        assertEquals(1, todos.size)

        val newTodo = Todo(
            id = 1,
            title = "test 2"
        )

        repository.insert(newTodo)
        assertEquals(1, repository.getTodos().first().size)

        repository.insert(
            Todo(
                id = 2,
                title = "test 2"
            )
        )
        assertEquals(2, repository.getTodos().first().size)
    }

    @Test
    fun getTodosLiveDataTest() {
        var result = repository.getTodosLiveData().getOrAwaitValue()
        assertEquals(0, result.size)

        val todo = Todo(
            title = "test 2"
        )

        runBlocking {
            repository.insert(todo)
        }

        result = repository.getTodosLiveData().getOrAwaitValue()
        assertEquals(1, result.size)

    }
}

/* Copyright 2019 Google LLC.
   SPDX-License-Identifier: Apache-2.0 */
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            data = o
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }

    this.observeForever(observer)

    // Don't wait indefinitely if the LiveData is not set.
    if (!latch.await(time, timeUnit)) {
        throw TimeoutException("LiveData value was never set.")
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}