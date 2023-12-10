package twentythree

class Day06 {
    init {
        firstPart()
        secondPart()
    }

    // 1195150 right
    private fun firstPart() {
        var times = Utils().parseLines("06")[0].split("\\s+".toRegex()).drop(1).map { it.toLong() }
        var distances = Utils().parseLines("06")[1].split("\\s+".toRegex()).drop(1).map { it.toLong() }
        var races = times.zip(distances).map { Race(it.first, it.second) }

        var result = 1L

        races.forEach {
            result *= LongRange(0, it.time).sumOf { time ->
                if ((time * 1) * (it.time - time) > it.distance) {
                    1L
                } else {
                    0L
                }
            }
        }

        println(result)
    }

    private fun secondPart() {
        var times = Utils().parseLines("06a")[0].split("\\s+".toRegex()).drop(1).map { it.toLong() }
        var distances = Utils().parseLines("06a")[1].split("\\s+".toRegex()).drop(1).map { it.toLong() }
        var races = times.zip(distances).map { Race(it.first, it.second) }

        var result = 1L

        races.forEach {
            result *= LongRange(0, it.time).sumOf { time ->
                if ((time * 1) * (it.time - time) > it.distance) {
                    1L
                } else {
                    0L
                }
            }
        }

        println(result)
    }

    data class Race(val time: Long, val distance: Long)
}