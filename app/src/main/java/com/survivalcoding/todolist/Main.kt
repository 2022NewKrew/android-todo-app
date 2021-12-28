package com.survivalcoding.todolist

data class Trader(val name: String, val city: String)

fun main() {
    val person = Person("홍길동", 10)
    val person2 = Person("홍길동", 10)

    val person3 = Person(person.name, person.age)

    val people = setOf(person, person2)

    val trader = Trader("test", "test")
    val trader2 = Trader("test", "test")

    val trader3 = trader.copy(city = "suwon")

    println(trader == trader2)
}
