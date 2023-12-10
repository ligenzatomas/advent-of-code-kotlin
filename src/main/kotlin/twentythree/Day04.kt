package twentythree

import kotlin.math.pow

class Day04 {

    init {
        firstPart()
        secondPart()
    }

    // 42191 too high
    // 21105 right
    private fun firstPart() {
        var lines = Utils().parseLines("04").map {
            it.split(":").last()
        }.map {
            val pair = it.split("|")

            Pair(
                pair.first().trim().split(" ").filter { it.trim().isNotEmpty() }.map { it.trim().toInt() },
                pair.last().trim().split(" ").filter { it.trim().isNotEmpty() }.map { it.trim().toInt() })
        }.map {
            it.second.intersect(it.first)
        }.map {
            if (it.isEmpty()) {
                0
            } else if (it.size == 1) {
                1
            } else {
                2.toDouble().pow((it.size-1).toDouble()).toInt()
            }
        }

        println(lines.sum())
    }

    // 1018 too low
    // 5329815 right
    private fun secondPart() {
        var linesWins = Utils().parseLines("04").map {
            it.split(":").last()
        }.map {
            val pair = it.split("|")

            Pair(
                pair.first().trim().split(" ").filter { it.trim().isNotEmpty() }.map { it.trim().toInt() },
                pair.last().trim().split(" ").filter { it.trim().isNotEmpty() }.map { it.trim().toInt() })
        }.map {
            Pair(it.second.intersect(it.first).size, 1)
        }.toMutableList()

        linesWins.forEachIndexed { index, pair ->
            for (n in 1..pair.second) {
                for (i in 1..pair.first) {
                    linesWins[index + i] = Pair(linesWins[index + i].first, linesWins[index + i].second + 1)
                }
            }
        }

        println(linesWins.sumOf { it.second })
    }
}