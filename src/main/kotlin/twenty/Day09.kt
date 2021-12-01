package twenty

class Day09 {
    constructor() {
        secondPart()
    }

    fun secondPart() {
        val inputs = Utils().parseLines("09").map { it.toLong() }.toList()

        var sum = 0L
        var startPosition = 0
        var actualPosition = 0

        while (startPosition < inputs.size) {
            while (sum < 1721308972 && actualPosition < inputs.size) {
                sum += inputs[actualPosition]
                actualPosition++
            }

            if (sum == 1721308972L) break
            else {
                startPosition++
                actualPosition = startPosition
                sum = 0
            }
        }

        println(startPosition.toString() + " " + actualPosition)

        val range = inputs.subList(startPosition, actualPosition)
        println("+++++++++++++")
        println(range.minOrNull().toString() + " " + range.maxOrNull())
        println((range.minOrNull()?.plus(range.maxOrNull()!!)).toString())
    }

    // 1721308972
    fun firstPart() {
        val inputs = Utils().parseLines("09").map { it.toLong() }.toList()
        val past = 25

        val iterator = inputs.subList(past, inputs.size).iterator().withIndex()
        var value = iterator.next()

        println(value.value)

        while (numberIsSumOfPartNumbers(value.value, inputs.subList(value.index, value.index + past))) {
            value = iterator.next()
        }

        println(value.index.toString() + " " + value.value)

    }

    fun numberIsSumOfPartNumbers(number: Long, past: List<Long>): Boolean {
        var position = 0

        past.forEach {
            for (i in past.indices) {
                if (i == position) continue
                if (it + past[i] == number) return true
            }
            position++
        }

        return false
    }
}