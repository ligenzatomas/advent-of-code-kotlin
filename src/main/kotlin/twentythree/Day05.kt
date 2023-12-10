package twentythree

class Day05 {
    init {
        firstPart()
        secondPart()
    }

    // 486613012 right
    private fun firstPart() {
        var seeds = Utils().parseLines("05").get(0).replace("seeds: ", "").split(" ").map { it.toLong() }.toMutableList()
        var transformers = mutableMapOf<String, List<To>>()

        var label = ""

        Utils().parseLines("05").drop(2).forEach { line ->
            if (line.endsWith(" map:")) {
                label = line.replace(" map:", "")
            } else if (line.isNotEmpty()) {
                var parts = line.split(" ")

                if (transformers.containsKey(label)) {
                    transformers[label] = transformers[label]!!.plus(To(parts[1].toLong(), parts[0].toLong(), parts[2].toLong()))
                } else {
                    transformers[label] = listOf(To(parts[1].toLong(), parts[0].toLong(), parts[2].toLong()))
                }
            }
        }

        var lowest = seeds.map { seed ->
            var result = seed

            transformers.forEach { (label, tos) ->
                tos.forEach { to ->
                    if (result >= to.source && result <= to.source + to.range - 1) {
                        result = result - to.source + to.destination
                    }
                }
            }

            result
        }.minOrNull()

        println(lowest)

    }

    // 3685504747 to high - last i was 629000000
    // 629464378 too high
    // 56931769 right
    private fun secondPart() {
        var seeds = Utils().parseLines("05").get(0)
            .replace("seeds: ", "")
            .split(" ")
            .chunked(2)
            .map { Seed(LongRange(it[0].toLong(), it[0].toLong() + it[1].toLong() - 1)) }
            .toMutableList()
        // seeds to pairs

        var transformers = mutableMapOf<String, List<To>>()

        var label = ""

        Utils().parseLines("05").drop(2).forEach { line ->
            if (line.endsWith(" map:")) {
                label = line.replace(" map:", "")
            } else if (line.isNotEmpty()) {
                var parts = line.split(" ")

                if (transformers.containsKey(label)) {
                    transformers[label] = transformers[label]!!.plus(To(parts[1].toLong(), parts[0].toLong(), parts[2].toLong()))
                } else {
                    transformers[label] = listOf(To(parts[1].toLong(), parts[0].toLong(), parts[2].toLong()))
                }
            }
        }

        var result: Long? = null
        var i = 0L

        while (result == null) {
            var the = i

            transformers.toList().reversed().toMap().forEach first@{ (label, tos) ->
                tos.forEach second@{ to ->
                    if (LongRange(to.destination, to.destination + to.range - 1).contains(the)) {
                        the = (the - to.destination) + to.source
                        return@first
                    }
                }
            }

            if (seeds.any { it.range.contains(the) }) {
                result = i
            }

            i++
        }

        println(result)
    }

    data class To(val source: Long, val destination: Long, val range: Long)
    data class Seed(val range: LongRange)
}