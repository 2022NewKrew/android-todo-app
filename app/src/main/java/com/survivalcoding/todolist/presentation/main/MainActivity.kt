package com.survivalcoding.todolist.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.todolist.databinding.ActivityMainBinding
import com.survivalcoding.todolist.domain.model.ToDo
import com.survivalcoding.todolist.presentation.createtodo.CreateToDoFragment
import com.survivalcoding.todolist.presentation.todolist.ToDoListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(binding.fragmentContainerView.id, ToDoListFragment.newInstance())
                .commit()
        }
    }

    fun navigateToCreateToDo(toDo: ToDo? = null) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainerView.id, CreateToDoFragment.newInstance(toDo))
            .addToBackStack(null)
            .commit()
    }
}