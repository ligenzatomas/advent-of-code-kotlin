package twentytwo

import java.util.*

class Day05 {
    init {
        firstPart()
        secondPart()
    }

    private fun firstPart() {
        val inputs = Utils().parseLines("05")

        val stackLoads = inputs.subList(0, 8)
        val stackStores = inputs[8].trim().split("   ").associate { it.toInt() to Stack(it.toInt()) }
        val moves = inputs.subList(10, inputs.size)

        stackLoads.reversed().forEach {
            for ( i in 2 until 35 step 4) {
                if (it.length >= i && !it[i].isWhitespace()) {
                    stackStores.get((i+2)/4)?.push(it[i-1])
                }
            }
        }

        moves.forEach {
            val move = """move (\d+) from (\d+) to (\d+)""".toRegex().find(it)?.groupValues

            if (move == null || move.size < 4 ) {
                throw RuntimeException("Bad move")
            }

            val stackFrom = move.get(2).toInt()
            val stackTo = move.get(3).toInt()
            val count = move.get(1).toInt()

            for ( i in 1..count) {
                stackStores.get(stackTo)?.push(stackStores.get(stackFrom)?.pop()!!)
            }
        }

        println(String(stackStores.map { it.value.pop() }.toCharArray()))
    }

    private fun secondPart() {
        val inputs = Utils().parseLines("05")

        val stackLoads = inputs.subList(0, 8)
        val stackStores = inputs[8].trim().split("   ").associate { it.toInt() to Stack(it.toInt()) }
        val moves = inputs.subList(10, inputs.size)

        stackLoads.reversed().forEach {
            for ( i in 2 until 35 step 4) {
                if (it.length >= i && !it[i].isWhitespace()) {
                    stackStores.get((i+2)/4)?.push(it[i-1])
                }
            }
        }

        moves.forEach {
            val move = """move (\d+) from (\d+) to (\d+)""".toRegex().find(it)?.groupValues

            if (move == null || move.size < 4 ) {
                throw RuntimeException("Bad move")
            }

            val stackFrom = move.get(2).toInt()
            val stackTo = move.get(3).toInt()
            val count = move.get(1).toInt()

            val charArray: MutableList<Char> = ArrayList()

            for ( i in 1..count) {
                stackStores.get(stackFrom)?.pop()?.let { it1 -> charArray.add(it1) }
            }

            for (c in charArray.reversed()) {
                stackStores.get(stackTo)?.push(c)
            }
        }

        println(String(stackStores.map { it.value.pop() }.toCharArray()))
    }

    class Stack(private val position: Int) {
        private val stack: java.util.Stack<Char> = Stack()

        fun push(c: Char) {
            stack.push(c)
        }

        fun pop(): Char {
            return stack.pop()
        }
    }
}