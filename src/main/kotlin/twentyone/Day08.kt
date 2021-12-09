package twentyone

import java.lang.RuntimeException

class Day08 {
    init {
        partTwo()
    }

    //   aa
    //  b   c
    //  b   c
    //   dd
    //  e   f
    //  e   f
    //   gg

    // od osmicky (7) odectu 6ti pismenne (dostanu trojci 0,6 nebo 9) a od toho odectu jednicku (2) pismena, tak dostanu cc hranu a ff hranu
    // od zbyvajicich dvou odectu 4 (ta co se da odecist je 0, tedy dd) a ta co zbyde je 9, tedy ee
    // to co mam odectu od ctyrky (4 pismena) a mám bb
    // to co mam odectu od osmicky (7 pismen) a mam gg
    fun partTwo() {
        val inputs = Utils().parseLines("08").toList().map {
            it.split(" | ")[0]
        }.map { it.split(' ') }

        val outputs = Utils().parseLines("08").toList().map {
            it.split(" | ")[1]
        }.map { it.split(' ') }

        var acum = 0

        inputs.forEachIndexed { index, line ->
            val aa = findAa(line)
            val cc = findCc(line)
            val dd = findDd(line, cc)
            val ee = findEe(line, cc, dd)
            val ff = findFf(line)
            val bb = findBb(line, cc, dd, ff)
            val gg = findGg(line, aa, bb, cc, dd, ee, ff)

            val number = Integer.parseInt(outputs[index].map { encode(it, aa, bb, cc, dd, ee, ff, gg) }.joinToString(""))
            println(number)

            acum += number
        }

        println(acum)
    }

    fun encode(str: String, aa:Char, bb:Char, cc: Char, dd: Char, ee: Char, ff: Char, gg: Char): String {
        val zero =  arrayListOf(aa, bb, cc, ee, ff, gg)
        val one =   arrayListOf(cc, ff)
        val two =   arrayListOf(aa, cc, dd, ee, gg)
        val three = arrayListOf(aa, cc ,dd ,ff, gg)
        val four =  arrayListOf(bb, cc, dd, ff)
        val five =  arrayListOf(aa, bb, dd, ff, gg)
        val six =   arrayListOf(aa, bb, dd, ee, ff, gg)
        val seven = arrayListOf(aa, cc, ff)
        val eight = arrayListOf(aa, bb, cc, dd, ee, ff, gg)
        val nine =  arrayListOf(aa, bb, cc, dd, ff, gg)

        if (str.all { one.contains(it) }) return "1"
        if (str.all { seven.contains(it) }) return "7"
        if (str.all { four.contains(it) }) return "4"
        if (str.all { two.contains(it) }) return "2"
        if (str.all { three.contains(it) }) return "3"
        if (str.all { five.contains(it) }) return "5"
        if (str.all { zero.contains(it) }) return "0"
        if (str.all { six.contains(it) }) return "6"
        if (str.all { nine.contains(it) }) return "9"
        if (str.all { eight.contains(it) }) return "8"

        throw RuntimeException("Chyba")
    }

    fun findAa(inputs: List<String>): Char {
        // dva proti třem - tak to třetí je horní aa hrana
        val seven = getNumberSeven(inputs)
        val one = getNumberOne(inputs)
        return seven.first { oneChar -> !one.contains(oneChar) }
    }

    fun findCc(inputs: List<String>): Char {
        val eight = getNumberEight(inputs).toList()
        val zeroSixNine = getNumbersZeroSixNine(inputs)

        val zeroSixNineDiff = zeroSixNine.map {
            eight.filter { e -> !it.contains(e) }
        }

        val one = getNumberOne(inputs).toList()

        return zeroSixNineDiff.map { one.intersect(it) }.filter { it.isNotEmpty() }.map { it.first() }.first()
    }

    fun findFf(inputs: List<String>): Char {
        val eight = getNumberEight(inputs).toList()
        val zeroSixNine = getNumbersZeroSixNine(inputs)

        val zeroSixNineDiff = zeroSixNine.map {
            eight.filter { e -> !it.contains(e) }
        }

        val one = getNumberOne(inputs).toList()

        return zeroSixNineDiff.filter { one.intersect(it).isNotEmpty() }.map { one.subtract(it) }.filter { it.isNotEmpty() }.map { it.first() }.first()
    }

    fun findDd(inputs: List<String>, cc: Char): Char {
        val eight = getNumberEight(inputs).toList()
        val zeroSixNine = getNumbersZeroSixNine(inputs)

        val zeroNineDiff = zeroSixNine.map {
            eight.filter { e -> !it.contains(e) }
        }.filter { !it.contains(cc) }

        val four = getNumberFour(inputs).toList()

        return zeroNineDiff.map { four.intersect(it) }.filter { it.isNotEmpty() }.map { it.first() }.first()
    }

    fun findEe(inputs: List<String>, cc: Char, dd: Char): Char {
        val eight = getNumberEight(inputs).toList()
        val zeroSixNine = getNumbersZeroSixNine(inputs)

        return zeroSixNine.map {
            eight.filter { e -> !it.contains(e) }
        }.filter { !it.contains(cc) && !it.contains(dd) }.map { it.first() }.first()
    }

    fun findBb(inputs: List<String>, cc: Char, dd: Char, ff: Char): Char {
        return getNumberFour(inputs).first { it != cc && it != dd && it != ff }
    }

    fun findGg(inputs: List<String>, aa:Char, bb:Char, cc: Char, dd: Char, ee: Char, ff: Char): Char {
        val arr = arrayOf(aa, bb, cc, dd, ee, ff)
        return getNumberEight(inputs).first { !arr.contains(it) }
    }

    fun getNumberOne(inputs: List<String>): String {
        return inputs.first { it.length == 2 }
    }

    fun getNumberFour(inputs: List<String>): String {
        return inputs.first { it.length == 4 }
    }

    fun getNumberSeven(inputs: List<String>): String {
        return inputs.first { it.length == 3 }
    }

    fun getNumberEight(inputs: List<String>): String {
        return inputs.first { it.length == 7 }
    }

    fun getNumbersZeroSixNine(inputs: List<String>): List<String> {
        return inputs.filter { it.length == 6 }
    }

    fun partOne() {
        val inputs = Utils().parseLines("08").toList().map {
            it.split(" | ")[1]
        }.map { it.split(' ') }.flatten()

        println(inputs.filter { it.length in intArrayOf(2,3,4,7) }.size)
    }
}