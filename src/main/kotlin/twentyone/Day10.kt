package twentyone

import java.math.BigInteger

class Day10 {
    init {
        partTwo()
    }

    // 3015539998
    fun partTwo() {
        val inputs = Utils().parseLines("10").toList().map { it.toCharArray() }.toList()

        var value: MutableList<BigInteger> = mutableListOf()

        inputs.forEachIndexed { index, chars ->
            val openedChars: MutableList<Char> = mutableListOf()
            getAllUnclosedChunk(chars[0], 0, chars, openedChars)

            var score = BigInteger.ZERO

            while (openedChars.isNotEmpty()) {
                score *= BigInteger.valueOf(5)
                score += BigInteger.valueOf(calculateUncompleteScore(openedChars.last()).toLong())

                openedChars.removeLast()
            }

            if (score > BigInteger.ZERO) value.add(score)
        }

        value.sort()

        println(value.get(value.size / 2))
    }

    fun calculateUncompleteScore(openingChar: Char): Int {
        return mapOf(
            Pair('(', 1),
            Pair('[', 2),
            Pair('{', 3),
            Pair('<', 4))
            .get(openingChar)!!
    }

    fun getAllUnclosedChunk(char: Char, index: Int, chars: CharArray, openedChars: MutableList<Char>) {
        var stop = false

        if (isCloseChar(char)) {
            if (isClosingChar(char, openedChars.last())) {
                openedChars.removeLast()
            } else {
                openedChars.clear()
                stop = true
            }
        }

        if (isOpenChar(char)) {
            openedChars.add(char)
        }

        if (index < chars.size - 1 && !stop) {
            getAllUnclosedChunk(chars[index+1], index+1, chars, openedChars)
        }
    }

    fun partOne() {
        val inputs = Utils().parseLines("10").toList().map { it.toCharArray() }.toList()

        var value = 0
        inputs.forEach { chars ->
            value += getValueOfUnclosedChunk(chars[0], 0, chars, mutableListOf())
        }

        println(value)
    }

    fun getValueOfUnclosedChunk(char: Char, index: Int, chars: CharArray, openedChars: MutableList<Char>): Int {
        var value = 0

        if (isCloseChar(char)) {
            if (isClosingChar(char, openedChars.last())) {
                openedChars.removeLast()
            } else {
                openedChars.removeLast()
                value = calculateSyntaxErrorScore(char)
            }
        }

        if (isOpenChar(char)) {
            openedChars.add(char)
        }

        return if (index < chars.size - 1) {
            value + getValueOfUnclosedChunk(chars[index+1], index+1, chars, openedChars)
        } else {
            value
        }
    }

    fun calculateSyntaxErrorScore(closingChar: Char): Int {
        return mapOf(
            Pair(')', 3),
            Pair(']', 57),
            Pair('}', 1197),
            Pair('>', 25137))
            .get(closingChar)!!
    }

    fun isClosingChar(closingChar: Char, openChar: Char): Boolean {
        return listOf(
            setOf('(', ')'),
            setOf('[', ']'),
            setOf('{', '}'),
            setOf('<', '>')
        ).any {
            it.contains(openChar) && it.contains(closingChar)
        }
    }

    fun isOpenChar(char: Char):Boolean {
        return setOf('(', '[', '{', '<').contains(char)
    }

    fun isCloseChar(char: Char):Boolean {
        return setOf(')', ']', '}', '>').contains(char)
    }
}