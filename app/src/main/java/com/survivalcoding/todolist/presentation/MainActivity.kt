package com.survivalcoding.todolist.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.survivalcoding.todolist.R
import com.survivalcoding.todolist.presentation.add.AddEditFragment
import com.survivalcoding.todolist.presentation.main.MainFragment
import com.survivalcoding.todolist.presentation.main.MainViewModel


class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace<MainFragment>(R.id.fragment_container_view)
                setReorderingAllowed(true)
                addToBackStack(null) // name can be null
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.action_add -> {
                viewModel.addTodo("test")
                true
            }
            R.id.action_delete -> {
                supportFragmentManager.commit {
                    replace<AddEditFragment>(R.id.fragment_container_view)
                    setReorderingAllowed(true)
                    addToBackStack(null) // name can be null
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}