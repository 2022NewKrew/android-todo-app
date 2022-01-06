package com.survivalcoding.todolist.domain.usecase

import java.text.SimpleDateFormat
import java.util.*

class ConvertDateFormatUseCase {

    operator fun invoke(time: Long): String {
        if (simpleDateFormat == null) {
            simpleDateFormat = SimpleDateFormat("yyyy/mm/dd HH:mm:ss", Locale.getDefault())
        }

        return simpleDateFormat?.format(Date(time)) ?: ""
    }

    companion object {
        private var simpleDateFormat: SimpleDateFormat? = null
    }
}