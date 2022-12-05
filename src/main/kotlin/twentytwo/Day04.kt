package twentytwo

class Day04 {
    init {
        firstPart()
        secondPart()
    }

    fun IntRange.fullyContains(other: IntRange): Boolean =
        (this.first <= other.first && this.last >= other.last) || (other.first <= this.first && other.last >= this.last)

    private fun firstPart() {
        val inputs = Utils().parseLines("04")

        println( inputs
            .map { Pair<String, String>(it.substringBefore(','), it.substringAfter(',')) }
            .map { Pair<IntRange, IntRange>(
                IntRange(it.first.substringBefore('-').toInt(), it.first.substringAfter('-').toInt()),
                IntRange(it.second.substringBefore('-').toInt(), it.second.substringAfter('-').toInt())
            ) }
            .filter { it.first.fullyContains(it.second) }
            .count())
    }

    private fun secondPart() {
        val inputs = Utils().parseLines("04")

        println( inputs
            .map { Pair<String, String>(it.substringBefore(','), it.substringAfter(',')) }
            .map { Pair<IntRange, IntRange>(
                IntRange(it.first.substringBefore('-').toInt(), it.first.substringAfter('-').toInt()),
                IntRange(it.second.substringBefore('-').toInt(), it.second.substringAfter('-').toInt())
            ) }
            .filter { (one, two) -> two.any{ one.contains(it) } }
            .count())
    }
}