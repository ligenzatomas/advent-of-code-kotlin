package twentytwo

class Day02 {
    init {
        /*
        results
        13565
        12424
         */
        firstPart()
        secondPart()
    }

    private fun firstPart() {
        val inputs = Utils().parseLines("02")

        println(inputs.map {
            val charArray = it.toCharArray()
            Game(Pair(charArray[0], charArray[2]))
        }.sumOf { it.valuate() })
    }

    private fun secondPart() {
        val inputs = Utils().parseLines("02")

        println(inputs.map {
            val charArray = it.toCharArray()
            Game(Pair(charArray[0], charArray[2]))
        }.sumOf { it.valuateSecond() })
    }

    class Game(private val pair: Pair<Char, Char>) {
        fun valuate(): Int {
            return when (pair.second) {
                'X' -> 1 + game()
                'Y' -> 2 + game()
                'Z' -> 3 + game()
                else -> throw RuntimeException("Invalid game")
            }
        }

        private fun game(): Int {
            when (pair.first) {
                'A' -> {
                    return when (pair.second) {
                        'Y' -> 6
                        'X' -> 3
                        else -> 0
                    }
                }
                'B' -> {
                    return when (pair.second) {
                        'X' -> 0
                        'Y' -> 3
                        else -> 6
                    }
                }
                'C' -> {
                    return when (pair.second) {
                        'X' -> 6
                        'Y' -> 0
                        else -> 3
                    }
                }
                else -> throw RuntimeException("Chyba")
            }

        }

        fun valuateSecond(): Int {
            return when (pair.second) {
                'X' -> 0 + gameSecond()
                'Y' -> 3 + gameSecond()
                'Z' -> 6 + gameSecond()
                else -> throw RuntimeException("Invalid game")
            }
        }

        private fun gameSecond(): Int {
            when (pair.first) {
                'A' -> {
                    return when (pair.second) {
                        'X' -> 3
                        'Y' -> 1
                        else -> 2
                    }
                }
                'B' -> {
                    return when (pair.second) {
                        'X' -> 1
                        'Y' -> 2
                        else -> 3
                    }
                }
                'C' -> {
                    return when (pair.second) {
                        'X' -> 2
                        'Y' -> 3
                        else -> 1
                    }
                }
                else -> throw RuntimeException("Chyba")
            }

        }
    }
}