package com.survivalcoding.todolist

data class Trader(val name: String, val city: String)
data class Transaction(val trader: Trader, val year: Int, val value: Int)

val transactions = listOf(
    Transaction(Trader("Brian", "Cambridge"), 2011, 300),
    Transaction(Trader("Raoul", "Cambridge"), 2012, 1000),
    Transaction(Trader("Raoul", "Cambridge"), 2011, 400),
    Transaction(Trader("Mario", "Milan"), 2012, 710),
    Transaction(Trader("Mario", "Milan"), 2012, 700),
    Transaction(Trader("Alan", "Cambridge"), 2012, 950),
)

fun main() {
    println("1. 2011년에 일어난 모든 트랙잭션을 찾아 값을 value 기준으로 오름차순으로 정리하시오")
    transactions.filter { it.year == 2011 }.sortedBy { it.value }.forEach(::println)

    println("\n2. 거래자가 근무하는 모든 도시를 중복 없이 나열하시오")
    transactions.map { it.trader.city }.distinct().forEach(::println)

    println("\n3. 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오")
    transactions.filter { it.trader.city == "Cambridge" }.map { it.trader }.distinct()
        .sortedBy { it.name }
        .forEach(::println)

    println("\n4. 모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오")
    transactions.map { it.trader.name }.distinct().sorted().forEach(::println)

    println("\n5. 밀라노에 거래자가 있는가?")
    println(transactions.any { it.trader.city == "Milan" })

    println("\n6. 케임브리지에 근무하는 거래자의 모든 트랙잭션값을 출력하시오")
    transactions.filter { it.trader.city == "Cambridge" }.forEach(::println)

    println("\n7. 전체 트랜잭션 중 최댓값은 얼마인가?")
    println(transactions.maxOf { it.value })

    println("\n8. 전체 트랜잭션 중 최솟값은 얼마인가?")
    println(transactions.minOf { it.value })
}