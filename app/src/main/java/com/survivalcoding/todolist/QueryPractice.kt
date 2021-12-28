package com.survivalcoding.todolist

annotation class QueryDescription(val description: String)

data class Trader(val name: String, val city: String)
data class Transaction(val trader: Trader, val year: Int, val value: Int)

fun main() {
    val transactions = listOf(
        Transaction(Trader("Brian", "Cambridge"), 2011, 300),
        Transaction(Trader("Raoul", "Cambridge"), 2012, 1000),
        Transaction(Trader("Raoul", "Cambridge"), 2011, 400),
        Transaction(Trader("Mario", "Milan"), 2012, 710),
        Transaction(Trader("Mario", "Milan"), 2012, 700),
        Transaction(Trader("Alan", "Cambridge"), 2012, 950),
    )

    val queries =
        listOf(::query1, ::query2, ::query3, ::query4, ::query5, ::query6, ::query7, ::query8)

    queries.forEach { query ->
        val queryDescription = query.annotations.find { it is QueryDescription } as QueryDescription
        print("${queryDescription.description}\n${query(transactions)}\n\n")
    }
}

@QueryDescription("1. 2011년에 일어난 모든 트랙잭션을 찾아 값을 value 기준으로 오름차순으로 정리하시오")
fun query1(transactions: List<Transaction>) =
    transactions.filter { it.year == 2011 }.sortedBy { it.value }

@QueryDescription("2. 거래자가 근무하는 모든 도시를 중복 없이 나열하시오")
fun query2(transactions: List<Transaction>) =
    transactions.map { it.trader.city }.distinct()

@QueryDescription("3. 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오")
fun query3(transactions: List<Transaction>) =
    transactions.map { it.trader }.distinct().filter { it.city == "Cambridge" }.sortedBy { it.name }

@QueryDescription("4. 모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오")
fun query4(transactions: List<Transaction>) =
    transactions.map { it.trader }.distinct().map { it.name }.sorted()

@QueryDescription("5. 밀라노에 거래자가 있는가?")
fun query5(transactions: List<Transaction>) =
    transactions.any { it.trader.city == "Milan" }

@QueryDescription("6. 케임브리지에 근무하는 거래자의 모든 트랙잭션값을 출력하시오")
fun query6(transactions: List<Transaction>) =
    transactions.filter { it.trader.city == "Cambridge" }

@QueryDescription("7. 전체 트랜잭션 중 최댓값을 얼마인가?")
fun query7(transactions: List<Transaction>) =
    transactions.maxOf { it.value }

@QueryDescription("8. 전체 트랜잭션 중 최솟값은 얼마인가?")
fun query8(transactions: List<Transaction>) =
    transactions.minOf { it.value }
