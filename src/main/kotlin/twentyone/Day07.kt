package twentyone

import java.util.stream.IntStream
import kotlin.math.absoluteValue

class Day07 {
    init {
        partTwo()
    }

    // 95495710
    // 95851339
    fun partTwo() {
        val inputs = Utils().parseLines("07").toList().get(0).split(',').map { Integer.parseInt(it) }.toIntArray()

        val mean = (inputs.size / 2).toInt()

        val map = mutableMapOf<Int, Int>()
        var i = 0

        while (i < 50) {
            //val elPlus = inputs.elementAt(mean+i)
            //val elMinus = inputs.elementAt(mean-i)

            val elPlus = mean+i
            val elMinus = mean-i

            val sumPlus = sumPartTwo(inputs, elPlus)
            map[elPlus] = sumPlus

            println("$elPlus $sumPlus")

            if (i != 0) {
                val sumMinus = sumPartTwo(inputs, elMinus)
                map[elMinus] = sumMinus
                println("$elMinus $sumMinus")
            }

            i++
        }

        val sortedMap = map.toList().sortedBy { (_, v) -> v }.toMap()

        println(sortedMap.toList()[0])
    }

    fun partOne() {
        val inputs = Utils().parseLines("07").toList().get(0).split(',').map { Integer.parseInt(it) }.toIntArray()
        inputs.sort()

        val mean = (inputs.size / 2).toInt()

        println(inputs.elementAt(mean))

        val map = mutableMapOf<Int, Int>()
        var i = 0

        while (i < 50) {
            val elPlus = inputs.elementAt(mean+i)
            val elMinus = inputs.elementAt(mean-i)

            val sumPlus = sum(inputs, elPlus)
            map[elPlus] = sumPlus

            println("$elPlus $sumPlus")

            if (i != 0) {
                val sumMinus = sum(inputs, elMinus)
                map[elMinus] = sumMinus
                println("$elMinus $sumMinus")
            }

            i++
        }

        val sortedMap = map.toList().sortedBy { (_, v) -> v }.toMap()

        println(sortedMap.toList()[0])
    }

    fun sum(inputs: IntArray, mean: Int): Int {
        var result = 0

        inputs.forEach {
            result += (it - mean).absoluteValue
        }

        return result
    }

    fun sumPartTwo(inputs: IntArray, mean: Int): Int {
        var result = 0

        inputs.forEach {
            val sum = sumBetweenValues(it, mean)
            //println("$it $mean $sum")
            result += sum
        }

        return result
    }

    fun sumBetweenValues(x: Int, y: Int): Int {
        return IntStream.range(0, ((x - y).absoluteValue + 1)).sum()
    }
}