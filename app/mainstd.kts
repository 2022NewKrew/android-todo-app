
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


// P1
println("===problem 1===")
transactions
        .filter{it.year==2011}
        .sortedBy { it.value }
        .forEach{println(it)}
// p2
println("===problem 2===")
transactions
        .map{it.trader.city}
        .toSet()
        .forEach{println(it)}
// p3
println("===problem 3===")
transactions
        .filter { it.trader.city=="Cambridge"}
        .map{it.trader.name}
        .toSet()
        .sortedBy { it }
        .forEach{println(it)}
// p4
println("===problem 4===")
transactions.asSequence()
        .map{it.trader.name}
        .toSet()
        .sortedBy { it }
        .forEach{println(it)}

// p5
println("===problem 5===")
println(transactions.filter { it.trader.city=="Milan"}.isEmpty())

// p6
println("===problem 6===")
transactions.filter { it.trader.city=="Cambridge"}.forEach { println(it) }

// p7
println("===problem 7===")
println(transactions.maxOf { it.value })

// p8
println("===problem 8===")
println(transactions.minOf { it.value })