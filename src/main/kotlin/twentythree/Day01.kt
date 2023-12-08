package twentythree

class Day01() {
    init {
        //firstPart()
        secondPart()
    }

    private fun firstPart() {
        // 56465
        println(getSums(Utils().parseLines("01")))
    }

    private fun secondPart() {
        // 55470 to low
        // 55929 to high
        // 56429
        println(
            getSums2(
                    Utils().parseLines("01")))
    }

    private fun getSums(inputs: List<String>): Int {
        return inputs.fold(mutableListOf<Int>()) {
                acc, line ->
            val first = line.first { it.isDigit() }
            val last = line.last { it.isDigit() }

            acc.add("${first}${last}".toInt())
            acc
        }
            .sum()
    }

    private fun getSums2(inputs: List<String>): Int {

        // iterate string in inputs and replace words with numbers
        return inputs.sumOf {
            var first = ""
            var last = ""
            var i = 0

            while (i < it.length) {
                val substring = it.substring(i)
                var result = ""

                if (startsDigitWord(substring)) {
                    result = getFirstDigitWord(substring.substring(0, countLengthOfDigitWord(substring)))
                } else if (it[i].isDigit()) {
                    result = it[i].toString()
                }

                if (result.isNotEmpty()) {
                    if (first.isEmpty()) {
                        first = result
                        last = result
                    } else {
                        last = result
                    }
                }

                i++
            }

            "${first}${last}".toInt()
        }
    }

    private fun startsDigitWord(input: String): Boolean {
        return input.startsWith("one") ||
                input.startsWith("two") ||
                input.startsWith("three") ||
                input.startsWith("four") ||
                input.startsWith("five") ||
                input.startsWith("six") ||
                input.startsWith("seven") ||
                input.startsWith("eight") ||
                input.startsWith("nine")
    }

    private fun countLengthOfDigitWord(input: String): Int {
        return when {
            input.startsWith("one") -> 3
            input.startsWith("two") -> 3
            input.startsWith("three") -> 5
            input.startsWith("four") -> 4
            input.startsWith("five") -> 4
            input.startsWith("six") -> 3
            input.startsWith("seven") -> 5
            input.startsWith("eight") -> 5
            input.startsWith("nine") -> 4
            else -> 0
        }
    }

    private fun getFirstDigitWord(input: String): String {
        val map = mapOf(
            "one" to "1",
            "two" to "2",
            "three" to "3",
            "four" to "4",
            "five" to "5",
            "six" to "6",
            "seven" to "7",
            "eight" to "8",
            "nine" to "9"
        )

        return map[input] ?: ""
    }
}