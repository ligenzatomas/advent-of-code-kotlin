package twentytwo

import java.math.BigInteger
import kotlin.math.floor

class Day11 {
    init {
        firstPart()
        secondPart()
    }

    // 69918
    private fun firstPart() {
        val monkeys = mutableListOf<Monkey>()

        monkeys.add(Monkey(
            0,
            mutableListOf(74, 73, 57, 77, 74),
            { it*11 },
            { when(it % 19.0 == 0.0) { true -> 6 false -> 7} }))
        monkeys.add(Monkey(
            1,
            mutableListOf(99, 77, 79),
            { it+8 },
            { when(it % 2.0 == 0.0) { true -> 6 false -> 0} }))
        monkeys.add(Monkey(
            2,
            mutableListOf(64, 67, 50, 96, 89, 82, 82),
            { it+1 },
            { when(it % 3.0 == 0.0) { true -> 5 false -> 3} }))
        monkeys.add(Monkey(
            3,
            mutableListOf(88),
            { it*7 },
            { when(it % 17.0 == 0.0) { true -> 5 false -> 4} }))
        monkeys.add(Monkey(
            4,
            mutableListOf(80, 66, 98, 83, 70, 63, 57, 66),
            { it+4 },
            { when(it % 13.0 == 0.0) { true -> 0 false -> 1} }))
        monkeys.add(Monkey(
            5,
            mutableListOf(81, 93, 90, 61, 62, 64),
            { it+7 },
            { when(it % 7.0 == 0.0) { true -> 1 false -> 4} }))
        monkeys.add(Monkey(
            6,
            mutableListOf(69, 97, 88, 93),
            { it*it },
            { when(it % 5.0 == 0.0) { true -> 7 false -> 2} }))
        monkeys.add(Monkey(
            7,
            mutableListOf(59, 80),
            { it+6 },
            { when(it % 11.0 == 0.0) { true -> 2 false -> 3} }))

        for (i in 0 until 20) {
            monkeys.forEach {monkey ->
                monkey.items.forEachIndexed { index: Int, _: Long ->
                    monkey.inspections++
                    val newItem = floor(monkey.operation(monkey.items[index]) / 3.0).toLong()
                    val newMonkey = monkey.test(newItem)

                    monkeys.first { it.name == newMonkey }.items.add(newItem)
                }

                monkey.items.clear()
            }
        }

        monkeys.sortByDescending { it.inspections }
        println(monkeys[0].inspections * monkeys[1].inspections)
    }

    // 19573408701
    private fun secondPart() {
        val monkeys = mutableListOf<BigMonkey>()

        monkeys.add(BigMonkey(
            0,
            mutableListOf(BigInteger.valueOf(74), BigInteger.valueOf(73), BigInteger.valueOf(57), BigInteger.valueOf(77), BigInteger.valueOf(74)),
            { it.multiply(BigInteger.valueOf(11)) },
            { when(it.mod(BigInteger.valueOf(19)).compareTo(BigInteger.ZERO) == 0) { true -> 6 false -> 7} }))
        monkeys.add(BigMonkey(
            1,
            mutableListOf(BigInteger.valueOf(99), BigInteger.valueOf(77), BigInteger.valueOf(79)),
            { it.add(BigInteger.valueOf(8)) },
            { when(it.mod(BigInteger.valueOf(2)).compareTo(BigInteger.ZERO) == 0) { true -> 6 false -> 0} }))
        monkeys.add(BigMonkey(
            2,
            mutableListOf(BigInteger.valueOf(64), BigInteger.valueOf(67), BigInteger.valueOf(50), BigInteger.valueOf(96), BigInteger.valueOf(89), BigInteger.valueOf(82), BigInteger.valueOf(82)),
            { it.add(BigInteger.valueOf(1)) },
            { when(it.mod(BigInteger.valueOf(3)).compareTo(BigInteger.ZERO) == 0) { true -> 5 false -> 3} }))
        monkeys.add(BigMonkey(
            3,
            mutableListOf(BigInteger.valueOf(88)),
            { it.multiply(BigInteger.valueOf(7)) },
            { when(it.mod(BigInteger.valueOf(17)).compareTo(BigInteger.ZERO) == 0) { true -> 5 false -> 4} }))
        monkeys.add(BigMonkey(
            4,
            mutableListOf(BigInteger.valueOf(80), BigInteger.valueOf(66), BigInteger.valueOf(98), BigInteger.valueOf(83), BigInteger.valueOf(70), BigInteger.valueOf(63), BigInteger.valueOf(57), BigInteger.valueOf(66)),
            { it.add(BigInteger.valueOf(4)) },
            { when(it.mod(BigInteger.valueOf(13)).compareTo(BigInteger.ZERO) == 0) { true -> 0 false -> 1} }))
        monkeys.add(BigMonkey(
            5,
            mutableListOf(BigInteger.valueOf(81), BigInteger.valueOf(93), BigInteger.valueOf(90), BigInteger.valueOf(61), BigInteger.valueOf(62), BigInteger.valueOf(64)),
            { it.add(BigInteger.valueOf(7)) },
            { when(it.mod(BigInteger.valueOf(7)).compareTo(BigInteger.ZERO) == 0) { true -> 1 false -> 4} }))
        monkeys.add(BigMonkey(
            6,
            mutableListOf(BigInteger.valueOf(69), BigInteger.valueOf(97), BigInteger.valueOf(88), BigInteger.valueOf(93)),
            { it.multiply(it) },
            { when(it.mod(BigInteger.valueOf(5)).compareTo(BigInteger.ZERO) == 0) { true -> 7 false -> 2} }))
        monkeys.add(BigMonkey(
            7,
            mutableListOf(BigInteger.valueOf(59), BigInteger.valueOf(80)),
            { it.add(BigInteger.valueOf(6)) },
            { when(it.mod(BigInteger.valueOf(11)).compareTo(BigInteger.ZERO) == 0) { true -> 2 false -> 3} }))

        val lmc = setOf<Int>(19, 2, 3, 17, 13, 7, 5, 11).fold(1) { acc: Int, i: Int -> acc * i }.toLong()

        for (i in 0 until 10000) {
            monkeys.forEach {monkey ->
                monkey.items.forEachIndexed { index: Int, _: BigInteger ->
                    monkey.inspections++
                    val newItem = monkey.operation(monkey.items[index]).mod(BigInteger.valueOf(lmc))
                    val newMonkey = monkey.test(newItem)

                    monkeys.first { it.name == newMonkey }.items.add(newItem)
                }

                monkey.items.clear()
            }
        }

        monkeys.sortByDescending { it.inspections }
        println(monkeys[0].inspections * monkeys[1].inspections)
    }

    class Monkey(
        val name: Int, val items: MutableList<Long>, val operation: (Long) -> Long, val test: (Long) -> Int, var inspections: Long = 0
    ) {
    }

    class BigMonkey(
        val name: Int, val items: MutableList<BigInteger>, val operation: (BigInteger) -> BigInteger, val test: (BigInteger) -> Int, var inspections: Long = 0
    ) {
    }

    // test data structure
    /*monkeys.add(Monkey(
            0,
            mutableListOf(79, 98),
            { it*19 },
            { when(it % 23 == 0) { true -> 2 false -> 3} }))
        monkeys.add(Monkey(
            1,
            mutableListOf(54, 65, 75, 74),
            { it+6 },
            { when(it % 19 == 0) { true -> 2 false -> 0} }))
        monkeys.add(Monkey(
            2,
            mutableListOf(79, 60, 97),
            { it*it },
            { when(it % 13 == 0) { true -> 1 false -> 3} }))
        monkeys.add(Monkey(
            3,
            mutableListOf(74),
            { it+3 },
            { when(it % 17 == 0) { true -> 0 false -> 1} }))*/
}