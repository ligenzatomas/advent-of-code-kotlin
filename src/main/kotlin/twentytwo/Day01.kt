package twentytwo

class Day01() {
    init {
        firstPart()
        secondPart()
    }

    private fun firstPart() {
        println(getSums().maxOrNull())
    }

    private fun secondPart() {
        println(getSums()
                .sortedDescending()
                .take(3)
                .sum())
    }

    private fun getSums(): List<Int> {
        val inputs = Utils().parseLines("01")

        return inputs.fold(mutableListOf(mutableListOf<Int>())) {
                acc, line ->
            if (line.isBlank()) {
                acc.add(mutableListOf())
            } else {
                acc.last().add(line.toInt())
            }
            acc
        }
            .map { list -> list.sum() }
    }
}