package com.survivalcoding.todolist.w1

fun main() {
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

    //1
    val ans1 = transactions.filter { it.year == 2011 }.sortedBy { it.value }
    println(ans1)

    //2
    //val ans2 = transactions.distinctBy { it.trader.city }.map { it.trader.city }
    val ans2 = transactions.map { it.trader.city }.distinct()
    println(ans2)

    //3
    val ans3 = transactions.filter { it.trader.city == "Cambridge" }.map { it.trader }
        .sortedBy { it.name }.distinctBy { it.name }
    println(ans3)

    //4
    val ans4 = transactions.distinctBy { it.trader.name }.map { it.trader.name }.sorted()
    println(ans4)

    //5
    val ans5 = transactions.count { it.trader.city == "Milan" } > 0
    println(ans5)

    //6
    val ans6 = transactions.filter { it.trader.city == "Cambridge" }.map { it.value }
    println(ans6)

    //7
    val ans7 = transactions.map { it.value }.maxOf { it }
    println(ans7)

    //8
    val ans8 = transactions.map { it.value }.minOf { it }
    println(ans8)
}