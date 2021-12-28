package com.survivalcoding.todolist

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class SimpleNumberListAdapter(private val numbers: List<Int>) : BaseAdapter() {
    private val clickedNumbers = mutableSetOf<Int>()

    override fun getCount() = numbers.size

    override fun getItem(position: Int) = numbers[position]

    override fun getItemId(position: Int) = numbers[position].toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemView = convertView
            ?: LayoutInflater.from(parent?.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false)
                .apply {
                    tag = ViewHolder(findViewById(android.R.id.text1))
                    setOnClickListener { view ->
                        onListItemClicked(view)
                    }
                }

        (itemView.tag as ViewHolder).numberTextView.text = numbers[position].toString()
        itemView.setBackgroundColor(if (isClickedNumber(numbers[position])) BACKGROUND_COLOR_POINT else BACKGROUND_COLOR_DEFAULT)

        return itemView
    }

    private fun onListItemClicked(view: View) {
        getNumberFromListItem(view).let {
            if (isClickedNumber(it)) {
                clickedNumbers.remove(it)
                view.setBackgroundColor(BACKGROUND_COLOR_DEFAULT)
            } else {
                clickedNumbers.add(it)
                view.setBackgroundColor(BACKGROUND_COLOR_POINT)
            }
        }
    }

    private fun getNumberFromListItem(view: View) =
        (view.tag as ViewHolder).numberTextView.text.toString().toInt()

    private fun isClickedNumber(number: Int) = number in clickedNumbers

    class ViewHolder(
        val numberTextView: TextView
    )

    companion object {
        private const val BACKGROUND_COLOR_DEFAULT = Color.WHITE
        private const val BACKGROUND_COLOR_POINT = Color.RED
    }
}