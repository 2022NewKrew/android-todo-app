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
// 1. 2011년에 일어난 모든 트랜잭션을 찾아 값을 value 기준으로 오름차순으로 정리하시오.
    transactions.filter { it.year == 2011 }
        .sortedBy { it.value }
        .forEach { println(it) }

    println()

// 2. 거래자가 근무하는 모든 도시를 중복 없이 나열하시오.
//    transactions.map { it.trader.city }
//        .toSet()
//        .forEach { println(it) }
    transactions.distinctBy { it.trader.city }
        .forEach { println(it.trader.city) }

    println()

// 3. 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오.
//    transactions.filter { it.trader.city == "Cambridge" }
//        .map { it.trader.name }.toSet()
//        .sorted()
//        .forEach { println(it) }
    transactions.filter { it.trader.city == "Cambridge" }
        .distinctBy { it.trader.name }
        .sortedBy { it.trader.name }
        .forEach { println(it.trader.name) }

    println()

// 4. 모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오.
//    transactions.map { it.trader.name }
//        .toSet()
//        .sorted()
//        .forEach { println(it) }
    transactions.distinctBy { it.trader.name }
        .sortedBy { it.trader.name }
        .forEach { println(it.trader.name) }

    println()

// 5. 밀라노에 거래자가 있는가?
    println(transactions.any { it.trader.city == "Milan" })

    println()

// 6. 케임브리지에 근무하는 거래자의 모든 트랜잭션 값을 출력하시오.
    transactions.filter { it.trader.city == "Cambridge" }
        .forEach { println(it) }

    println()

// 7. 전체 트랜잭션 중 최댓값은 얼마인가?
    val maxValue = transactions.maxOf { it.value }
    println(maxValue)
    transactions.filter { it.value == maxValue }
        .forEach { println(it) }

    println()

// 8. 전체 트랜잭션 중 최솟값은 얼마인가?
    val minValue = transactions.minOf { it.value }
    println(minValue)
    transactions.filter { it.value == minValue }
        .forEach { println(it) }
}

/*
 * take(n: Int): returns a list containing first n elements.
 *               앞에서 n 개의 요소를 리스트 형태로 반환한다.
 *
 * drop(n: Int): returns a list containing all elements except first n elements.
 *               앞에서 n 개를 제외한 나머지 요소들을 리스트 형태로 반환한다.
 *
 * distinct(): returns a list containing only distinct elements from the given array.
 *             Among equal elements of the given array, only the first one will be present in the resulting list.
 *             The elements in the resulting list are in the same order as they were in the source array.
 *             중복되는 요소를 제외하고 리스트 형태로 반환한다. 같은 요소 중에서 첫번째 요소를 선택하고, 원래의 순서를 유지한다.
 *
 * zip(other: Iterable<R>, transform: (a: T, b: R) -> V): returns a list of values built from the elements of this collection and the other collection with the same index using the provided transform function applied to each pair of elements.
 *                                                        the returned list has length of the shortest collection.
 *                                                        현재 컬렉션의 요소들과 인덱스가 같 other 컬렉션의 요소를 페어로 한 transform 함수의 결과값을 반환한다. 가장 짧은 길이의 컬렉션의 길이를 따른다.
 *                                                        transform 이 없으면, this 컬렉션과 other 컬렉션의 페어 리스트 반환.
 * unzip(): returns a pair of lists, where first list is built from the first values of each pair from this array, second list is built from the second values of each pair from this array.
 *          페어로 구성된 리스트에서 첫번째 요소만을 모은 리스트와, 두번째 요소만을 모은 리스트의 페어를 반환한다.
 *
 * reduce(operation: (key: K, accumulator: S, element: T) -> S): return a map associating the key of each group with the result of accumulating the group elements.
 *                                                               accumulates value starting with the first element and applying operation from left to right to current accumulator value and each element.
 *                                                               key 와 S 의 맵 형태로 반환한다. key 가 없으면 S 만 반환. accumulator 는 첫번째 요소로 시작한다.
 */