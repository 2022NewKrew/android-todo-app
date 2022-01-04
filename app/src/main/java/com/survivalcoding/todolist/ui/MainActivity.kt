package com.survivalcoding.todolist.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.survivalcoding.todolist.R
import com.survivalcoding.todolist.ui.main.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace<MainFragment>(R.id.fragment_container_view)
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view, fragment)
            .setReorderingAllowed(true)
            .addToBackStack(null)
            .commit()
    }
}