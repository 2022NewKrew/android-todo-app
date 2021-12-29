package com.survivalcoding.todolist.model

import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallBack(
    private val oldData: List<Task>,
    private val newData: List<Task>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldData.size
    }

    override fun getNewListSize(): Int {
        return newData.size
    }

    // 두 객체가 같은 항목인지 여부를 결정한다.
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData[oldItemPosition].id == newData[newItemPosition].id
    }

    // areItemsTheSame 가 true 를 반환하는 경우에만 호출한다.
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData[oldItemPosition] == newData[newItemPosition]
    }
}