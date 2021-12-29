package com.survivalcoding.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.survivalcoding.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var toDoList: List<ToDo> = (0L..30L).map {
        ToDo(
            id = it,
            title = "To Do List 만들기 $it"
        )
    }

    private val adapter =
        ToDoListAdapter(
            toDoList = toDoList,
            onItemCheckedChanged = ::onListItemCheckedChanged
        )


    private fun onListItemCheckedChanged(toDo: ToDo, isChecked: Boolean) {
        changeDoneState(toDo.id, isChecked)
    }

    private fun changeDoneState(targetId: Long, isDone: Boolean) {
        var position = 0

        toDoList = toDoList.mapIndexed { index, toDo ->
            if (toDo.id == targetId) {
                position = index
                toDo.copy(isDone = isDone)
            } else {
                toDo
            }
        }

        adapter.submitItem(toDoList, position)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.toDoListRecyclerView.adapter = adapter

        savedInstanceState?.let {
            toDoList = it.getParcelableArray(TO_DO_LIST)?.toList() as List<ToDo>
            adapter.submitData(toDoList)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArray(TO_DO_LIST, toDoList.toTypedArray())
        super.onSaveInstanceState(outState)
    }


    companion object {
        private const val TO_DO_LIST = "TO_DO_LIST"
    }
}