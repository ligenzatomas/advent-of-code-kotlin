package twentythree

class Day08 {

    init {
        firstPart()
        secondPart()
    }

    // 17287 right
    private fun firstPart() {
        val steps = Utils().parseLines("08").get(0).map { it }

        val elements = Utils().parseLines("08").drop(2).map {
            Element(it.substring(0, 3), it.substring(7, 10), it.substring(12, 15))
        }

        elements.forEach { element ->
            val left = elements.first { it.name == element.leftString }
            val right = elements.first { it.name == element.rightString }

            element.left = left
            element.right = right
        }

        var i = 0
        var current = elements.first { it.name == "AAA" }

        while (current.name != "ZZZ") {
            steps.forEach { step ->
                i++

                if (current.name == "ZZZ") {
                    println(i)
                    return
                }

                if (step == 'L') {
                    current = current.left!!
                } else {
                    current = current.right!!
                }
            }

            //println("all steps")
        }

        println(i)
    }

    // 2515374 too low
    // 18625484023687 right
    private fun secondPart() {
        val steps = Utils().parseLines("08").get(0).map { it }

        val elements = Utils().parseLines("08").drop(2).map {
            Element(it.substring(0, 3), it.substring(7, 10), it.substring(12, 15))
        }

        elements.forEach { element ->
            val left = elements.first { it.name == element.leftString }
            val right = elements.first { it.name == element.rightString }

            element.left = left
            element.right = right
        }

        val currents = elements.filter { it.name.endsWith('A') }

        val pairs = currents.map {
            var i = 0
            var current = it

            while (!current.name.endsWith('Z')) {
                steps.forEach { step ->
                    i++

                    if (current.name.endsWith('Z')) {
                        return@forEach
                    }

                    if (step == 'L') {
                        current = current.left!!
                    } else {
                        current = current.right!!
                    }
                }
            }

            Pair(it, i.toLong())
        }

        println(findLCMOfListOfNumbers(pairs.map { it.second }))
    }

    fun findLCMOfListOfNumbers(numbers: List<Long>): Long {
        var result = numbers[0]
        for (i in 1 until numbers.size) {
            result = findLCM(result, numbers[i])
        }
        return result
    }

    fun findLCM(a: Long, b: Long): Long {
        val larger = if (a > b) a else b
        val maxLcm = a * b
        var lcm = larger
        while (lcm <= maxLcm) {
            if (lcm % a == 0L && lcm % b == 0L) {
                return lcm
            }
            lcm += larger
        }
        return maxLcm
    }

    class Element(var name: String, var leftString: String, var rightString: String) {
        var left: Element? = null
        var right: Element? = null
    }
}