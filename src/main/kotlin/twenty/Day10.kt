package twenty

import java.util.*
import kotlin.math.pow

class Day10 {
    constructor() {
        sec()
    }

    var possibilities = 1L
    var possibilities2 = 1L
    var possibilities3 = mutableMapOf<Long, LinkedList<Long>>()

    fun sec() {
        val inputs = Utils().parseLines("10").map { it.toLong() }.toList().sorted()
        val linkedList = LinkedList(inputs)
        linkedList.push(0L)
        linkedList.add(linkedList.maxOrNull()?.plus(3L))

        val values = mutableListOf<Long>()

        println(linkedList)

        var inRow = 0;

        for (i in linkedList.indices) {
            if (i + 1 >= linkedList.size) break

            if (linkedList[i + 1] - linkedList[i] == 1L) {
                inRow++
            } else {
                if (inRow > 0) {
                    values.add(inRow.toLong())
                    inRow = 0
                }
            }
        }

        println(values)

        val toCount = values.map {
            if (it == 4L) 7L else it
        }.map { if (it == 3L) 4L else it }.toList()

        println(toCount)
        println(toCount.reduce { acc, unit -> acc * unit })
    }

    // 16452712980283392 to high
    // 2199023255552 to high
    // 1322306994176 CORRECT
    // 1099511627776 incorrect
    // 313789648032 incorrect
    // 17179869184 to low
    fun secondPart() {
        val inputs = Utils().parseLines("101").map { it.toLong() }.toList().sorted()

        val linkedList = LinkedList(inputs)

        linkedList.add(linkedList.maxOrNull()?.plus(3L))

        possibilities3[1L] = LinkedList()

        var restNumbers = linkedList.subList(0, 5)
        restNumbers = LinkedList(listOf(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L))
        println(restNumbers)

        brutalForce(0L, restNumbers)
        println("------------------ " + possibilities2)
        //println(possibilities3)


        /*linkedList.push(0L)
        countThisPossibility(linkedList)
        println("------------------ " + possibilities)*/
    }

    fun brutalForce(actualNumber: Long, restNumbers: List<Long>) {
        for (i in restNumbers.indices) {
            if (restNumbers[i] - actualNumber <= 3L) {
                if (i > 0) {
                    possibilities2++
                }
                if (restNumbers.size > 1) {
                    brutalForce(restNumbers[i], restNumbers.subList(i + 1, restNumbers.size))
                }

            } else break
        }
    }

    fun countThisPossibility(restNumbers: List<Long>) {
        //if (restNumbers.isEmpty()) return
        /*if (possibilities % 1000000L == 0L) {
            println(possibilities)
            println(restNumbers.size)
        }*/

        println(restNumbers)

        var x = 0;

        for (i in restNumbers.indices) {
            //var j = 1L

            if (x > 0) {
                x--
                continue
            }

            //println(i.toString() + " " + restNumbers[i])

            while (x + i + 1 < restNumbers.size && restNumbers[x + i + 1] - restNumbers[x + i] == 1L) {
                x++
            }

            //if (restNumbers[i] - actualNumber <= 3) inRow++
            //if (i + 2 < restNumbers.size && restNumbers[i + 2] - restNumbers[i] <= 3) inRow++
            //if (i + 3 < restNumbers.size && restNumbers[i + 3] - restNumbers[i] <= 3) inRow++
            //if (i + 3 < restNumbers.size && restNumbers[i + 3] - actualNumber <= 3) j++

            //println(x.toString() + " " + possibilities + " " + i + " " + restNumbers[i] + " " + restNumbers.size)
            //println(x.toString() + " " + possibilities + " " + i + " " + restNumbers[i] + " " + restNumbers.size)
            //println()

            //if (j > 1) i += (j - 1)

            if (x >= 1) {
                x--
                println(i.toString() + " " + restNumbers[i].toString() + " " + (x).toString() + " " + possibilities)
                possibilities *= 2.toDouble().pow(x).toLong()
            }
        }
    }

    fun firstPart() {
        val inputs = Utils().parseLines("10").map { it.toLong() }.toList().sorted()

        var oneJolt = 0
        var threeJolts = 0
        var lastIndex = 0

        for (i in inputs.indices) {
            if (i == 0) {
                if (inputs[i] - 0L == 1L) oneJolt++
                if (inputs[i] - 0L == 3L) threeJolts++

                if (inputs[i] - 0L > 3L) println("!!!!!!!")
                continue
            }

            println(inputs[i] - inputs[lastIndex])

            if (inputs[i] - inputs[lastIndex] == 1L) oneJolt++
            if (inputs[i] - inputs[lastIndex] == 3L) threeJolts++

            if (inputs[i] - inputs[lastIndex] > 3L) println("!!!!!!!")

            lastIndex++
        }

        println(inputs.size)
        println(oneJolt)
        println(threeJolts)

        threeJolts++

        println(oneJolt * threeJolts)

    }
}