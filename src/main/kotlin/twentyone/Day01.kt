package twentyone

import java.io.File

class Day01() {

    init {
        secondPart()
    }

    private fun secondPart() {
        val inputs = readFileAsLinesUsingUseLines("/home/cml/Dropbox/docMy/workspace/AdventOfCode/src/main/resources/2021/day01a.txt")
        val ints = inputs.map { it.toInt() }
        val values = mutableListOf<Int>()

        for (i in ints.indices) {
            if (ints.size > (i + 2)) {
                values.add((ints[i] + ints[i + 1] + ints[i + 2]))
            }
        }

        //println(values)
        println(values.size)

        var countIncrease = 0
        var lastValue: Int? = null

        values.forEach {
            if (lastValue != null && it > lastValue!!) {
                countIncrease++
            }

            lastValue = it
        }

        println("Result is $countIncrease")
    }

    private fun firstPart() {
        val inputs = readFileAsLinesUsingUseLines("/home/cml/Dropbox/docMy/workspace/AdventOfCode/src/main/resources/2021/day01.txt")
        val ints = inputs.map { it.toInt() }
        var countIncrease = 0
        var lastValue: Int? = null

        ints.forEach {
            if (lastValue != null && it > lastValue!!) {
                countIncrease++
            }

            lastValue = it
        }

        println("Result is $countIncrease")
    }

    private fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }
}