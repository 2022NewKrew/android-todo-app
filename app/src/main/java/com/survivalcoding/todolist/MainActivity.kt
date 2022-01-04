package com.survivalcoding.todolist

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.survivalcoding.todolist.presentation.add.AddEditFragment
import com.survivalcoding.todolist.presentation.main.MainFragment
import com.survivalcoding.todolist.presentation.MainViewModel


class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            navigateTo<MainFragment>()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.action_delete -> {
                navigateTo<AddEditFragment>()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private inline fun <reified T : Fragment> navigateTo() {
        supportFragmentManager.commit {
            replace<T>(R.id.fragment_container_view)
            setReorderingAllowed(true)
            addToBackStack(null) // name can be null
        }
    }

}