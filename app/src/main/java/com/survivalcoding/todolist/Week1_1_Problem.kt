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

/*
    [스트림 함수]
    컬렉션, 배열등의 저장 요소를 하나씩 참조하며 함수형 인터페이스(람다식)를 적용하며
    반복적으로 처리할 수 있도록 해주는 기능.
    불필요한 코딩을 걷어내고 직관성을 높인 함수.
 */

fun main() {
    solveProblem()
    //playWithStream() - 문제 외 스트림 함수 구현 부분
}

fun solveProblem() {
    // 1. 2011년에 일어난 모든 트랙잭션을 찾아 값을 value 기준으로 오름차순으로 정리하시오
    println("[Problem 1]")
    transactions.filter { it.year == 2011 }//2011년 필터링
        .sortedBy { it.value }//value에 따라
        //.sortedByDescending { it.value } // 내림차순
        .forEach { println(it) }

    // 2. 거래자가 근무하는 모든 도시를 중복 없이 나열하시오
    println("\n[Problem 2]")
    transactions.map { it.trader.city }//map: 컬렉션 내 인자를 다른 값으로 변경, city 값만 추가
        .distinct()//중복 제거
        .forEach { println(it) }

    // 3. 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오
    println("\n[Problem 3]")
    transactions.filter { it.trader.city == "Cambridge" }
        .map { it.trader.name }
        .distinct()
        .sorted()
        .forEach { println(it) }

    // 4. 모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오
    println("\n[Problem 4]")
    transactions.map { it.trader.name }
        .distinct()
        .sorted()
        .forEach { println(it) }

    // 5. 밀라노에 거래자가 있는가?
    println("\n[Problem 5]")
    println(transactions.any { it.trader.city == "Milan" })//Milan에 거래자가 있으면 True 반

    // 6. 케임브리지에 근무하는 거래자의 모든 트랙잭션값을 출력하시오
    println("\n[Problem 6]")
    transactions.filter { it.trader.city == "Cambridge" }
        .forEach { println(it) }

    // 7. 전체 트랜잭션 중 최댓값을 얼마인가?
    println("\n[Problem 7]")
    println(transactions.maxOf { it.value })

    // 8. 전체 트랜잭션 중 최솟값은 얼마인가?
    println("\n[Problem 8]")
    println(transactions.minOf { it.value })
}

fun playWithStream() {
    //take: 받은 인자 개수 만큼 앞에서부터 받은 인자로 이뤄진 리스트 반환
    println("\n[take]")
    transactions.take(2)
        .forEach { println(it) }
    println("\n[takeLast - 정렬은 작은 인스부터]")
    transactions.takeLast(3)
        .forEach { println(it) }
    println("\n[takeWhile]")
    transactions.takeWhile { it.value <= 500 }
        .forEach { println(it) }
    println("\n[takeLastWhile]")
    transactions.takeLastWhile { it.value >= 500 }
        .forEach { println(it) }

    //drop: take와 반대
    println("\n[drop]")
    transactions.drop(2)
        .forEach { println(it) }
    println("\n[dropLast]")
    transactions.dropLast(3)
        .forEach { println(it) }

    //distinctBy: 조건에 맞춰서 중복 제거
    println("\n[distinctBy]")
    transactions.distinctBy { it.trader.city }
        .forEach { println(it) }

    //None: any와 반대, 조건에 맞는게 없으면 True 아님 False
    println("\nis Milan None: ${transactions.none { it.trader.city == "Milan" }}")
    //first: 조건에 맞는 첫번째 인자 리턴
    println("first over 500: ${transactions.first { it.value >= 500 }}")
    //last: 조건에 맞는 마지막 인자 리턴
    println("last below 500: ${transactions.last { it.value <= 500 }}")
    //count: 조건에 맞는 인자 개수 리턴
    println("count over 500: ${transactions.count { it.value >= 500 }}")
    println()

    val example = listOf(1, 2, 3, 4, 5)
    val example2 = listOf(6, 7, 8, 9, 10)
    //reduce: 리스트의 수 식에 따라 합쳐서 리턴
    println("reduce: ${example.reduce { acc, s -> acc + s }}")
    //fold: reduce랑 비슷한데 초기값 지정
    println("fold: ${example.fold(100) { acc, s -> acc + s }}")
    //average: list의 평균
    println("average: ${example.average()}")
    //zip: 2개의 list를 pair 형태로 합치기
    println("zip: ${example.zip(example2)}")
    println("zip: ${example.zip(example2) { n1, n2 -> "$n1 !! $n2" }}")
}