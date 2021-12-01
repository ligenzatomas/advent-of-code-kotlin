package twenty

import java.io.File
import java.util.stream.IntStream
import kotlin.streams.toList

class Day05 {
    constructor() {
        secondPart()
    }

    fun secondPart() {
        val inputs = readFileAsLinesUsingUseLines("/home/tomasligenza/Dropbox/docMy/workspace/AdventOfCode/src/main/resources/day05.txt")

        val translated = inputs.map {
            val decimalFirst = getDecimalNumber(getFirstPartTranslated(it))
            val decimalSecond = getDecimalNumber(getSecondPartTranslated(it))

            decimalFirst * 8 + decimalSecond
        }.toHashSet()

        println(IntStream.rangeClosed(1, 1000).filter {
            !translated.contains(it)
        }.toList())

    }

    fun firstPart() {
        val inputs = readFileAsLinesUsingUseLines("/home/tomasligenza/Dropbox/docMy/workspace/AdventOfCode/src/main/resources/day05.txt")
        var highest = 0

        inputs.forEach {
            println(getFirstPartTranslated(it))
            println(getSecondPartTranslated(it))

            val decimalFirst = getDecimalNumber(getFirstPartTranslated(it))
            val decimalSecond = getDecimalNumber(getSecondPartTranslated(it))

            if (decimalFirst * 8 + decimalSecond > highest) highest = decimalFirst * 8 + decimalSecond
        }

        println(highest)
    }

    fun getFirstPartTranslated(it: String): Long {
        return it.substring(0, 7).replace('F', '0').replace('B', '1').toLong()
    }

    fun getSecondPartTranslated(it: String): Long {
        return it.substring(7, 10).replace('L', '0').replace('R', '1').toLong()
    }

    fun getDecimalNumber(binaryNumber: Long): Int {
        var binaryNumber = binaryNumber
        var decimalNo = 0
        var power = 0

        while (binaryNumber > 0) {
            val r = binaryNumber % 10
            decimalNo = (decimalNo + r * Math.pow(2.0, power.toDouble())).toInt()
            binaryNumber /= 10
            power++
        }
        return decimalNo
    }

    private fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }
}