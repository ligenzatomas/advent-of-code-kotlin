package twentyone

import java.math.BigInteger

class Day14 {
    init {
        //partOne()
        partTwo()
    }

    fun partTwo() {
        val inputs = Utils().parseLines("14").toList()
        val rules = inputs.filter { it.contains("->") }.map { PairRule(it[0], it[1], it.substring(6, 7)[0]) }.toSet()

        val template = inputs.first()
        var result: MutableMap<Pair, BigInteger> = mutableMapOf()
        var x = 0

        while (x < template.length - 1) {
            val pair = Pair(template[x], template[x+1])
            result.putIfAbsent(pair, BigInteger.ZERO)
            result[pair] = result[pair]!! + BigInteger.ONE
            x++
        }

        var y = 1

        while (y <= 40) {
            println(y)
            val step: MutableMap<Pair, BigInteger> = mutableMapOf()

            result.forEach {
                val rule: PairRule? = rules.firstOrNull { rule -> rule.equalsPair(it.key) }

                if (rule != null) {
                    val insertion = rule.insertion!!

                    val pair = Pair(it.key.first, insertion)
                    step.putIfAbsent(pair, BigInteger.ZERO)
                    step[pair] = step[pair]!! + it.value

                    val pair2 = Pair(insertion, it.key.second)
                    step.putIfAbsent(pair2, BigInteger.ZERO)
                    step[pair2] = step[pair2]!! + it.value
                } else {
                    step[it.key] = step[it.key]!! + it.value
                }
            }

            result = step
            y++
        }

        val charMap: MutableMap<Char, BigInteger> = mutableMapOf()
        charMap[template.first()] = BigInteger.ONE

        for (entry: MutableMap.MutableEntry<Pair, BigInteger> in result) {
            charMap.putIfAbsent(entry.key.second, BigInteger.ZERO)
            charMap[entry.key.second] = charMap[entry.key.second]!! + entry.value
        }

        println(charMap.maxOf { it.value } - charMap.minOf { it.value })
    }

    data class PairRule(val first: Char, val second: Char) {
        var insertion: Char? = null

        constructor(first: Char, second: Char, insertion: Char) : this(first, second) {
            this.insertion = insertion
        }

        fun equalsPair(pair: Pair): Boolean {
            return pair.first == first && pair.second == second
        }
    }

    data class Pair(val first: Char, val second: Char)

    /*fun polymerize(input: String, rules: Set<Rule>, step: Int): String {
        if (step >= 11) {
            return input
        }

        val rule = rules.firstOrNull{ it.equalsPair(input) }

        return if (rule != null) {
            val result = rule.insert(input)
            polymerize(input[0].toString() + result, rules, step+1) + polymerize(result + input[1].toString(), rules, step + 1).drop(1)
        } else {
            input
        }
    }*/

    fun partOne() {
        val inputs = Utils().parseLines("14a").toList()

        var template = inputs.first()
        val rules = inputs.filter { it.contains("->") }.map { Rule(it.substring(0, 2), it.substring(6, 7)[0]) }.toSet()

        var i = 1

        while (i <= 10) {
            template = template.foldIndexed("") { index: Int, acc: String, c: Char ->
                if (index < (template.length - 1)) {
                    val templatePair = template[index].toString() + template[index+1].toString()
                    var rule = rules.firstOrNull{ it.equalsPair(templatePair) }

                    if (rule != null) {
                        return@foldIndexed acc + rule.apply(templatePair)
                    }
                }

                return@foldIndexed acc + c.toString()
            }

            i++
        }

        val charMap: MutableMap<Char, BigInteger> = mutableMapOf()

        for (c: Char in template) {
            if (!charMap.contains(c)) {
                charMap[c] = BigInteger.ZERO
            }

            charMap[c] = charMap[c]!! + BigInteger.ONE
        }

        println(charMap.maxOf { it.value } - charMap.minOf { it.value })
    }

    data class Rule(val pair: String, val insertion: Char) {
        fun equalsPair(templatePair: String): Boolean {
            return templatePair == pair
        }

        fun apply(templatePair: String): String {
            if (templatePair == pair) {
                return templatePair[0].toString() + insertion.toString()
            }

            return templatePair
        }

        fun insert(templatePair: Pair): String {
            if ((templatePair.first.toString() + templatePair.second.toString()) == pair) {
                return insertion.toString()
            }

            return ""
        }
    }
}