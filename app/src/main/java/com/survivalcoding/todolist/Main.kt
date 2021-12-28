package com.survivalcoding.todolist

fun main() {
    print("hello World")
    print("hello World")

    var items = listOf(1, 2, 3, 4, 5)   // 변수
    val items2 = listOf(1, 2, 3, 4, 5)   // 상수

    for (item in items) {
        print(item)
    }

    items.filter { it % 2 == 0 }.forEach(::print)
}
