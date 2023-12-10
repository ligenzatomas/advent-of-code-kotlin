package twentythree

class Day09 {

    init {
        firstPart()
        secondPart()
    }

    // 1758990221 too high
    // 1666172641 right
    private fun firstPart() {
        val lines = Utils().parseLines("09")

        println( lines.map {
            var items = it.split(" ")
                .map { it.toLong() }
                .toMutableList()
            var result = mutableListOf<MutableList<Long>>()
            result.add(items)

            while (!result.last().all { it == 0L }) {
                result.add(result.last().zipWithNext()
                    .map { it.second - it.first }
                    .toMutableList())
            }

            val resultReversed = result.reversed()

            resultReversed.forEachIndexed { index, longs ->
                if (index == 0) {
                    longs.add(0)
                } else {
                    longs.add(longs.last() + resultReversed[index - 1].last())
                }
            }

            resultReversed.last().last()
        }.sum())
    }

    // 3791 too high
    // -31 not right
    // 933 right
    private fun secondPart() {
        val lines = Utils().parseLines("09")

        println( lines.map {
            var items = it.split(" ")
                .map { it.toLong() }
                .toMutableList()
            var result = mutableListOf<MutableList<Long>>()
            result.add(items)

            while (!result.last().all { it == 0L }) {
                result.add(result.last().zipWithNext()
                    .map { it.second - it.first }
                    .toMutableList())
            }

            val resultReversed = result.reversed()

            resultReversed.forEachIndexed { index, longs ->
                if (index == 0) {
                    longs.add(0, 0)
                } else {
                    longs.add(0, longs.first() - resultReversed[index - 1].first())
                }
            }

            resultReversed.last().first()
        }.sum())
    }
}