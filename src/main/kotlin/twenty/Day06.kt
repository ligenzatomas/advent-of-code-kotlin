package twenty

class Day06 {
    constructor() {
        secondPart()
    }

    fun secondPart() {
        val inputs = Utils().parseLines("06");

        var sum = 0
        var map = mutableMapOf<Char, Int>()
        var countOfPersons = 0

        for (it in inputs) {

            if (it.isEmpty()) {
                println(map)
                println(countOfPersons)
                println(map.filter { it.value == countOfPersons }.count())
                sum += map.filter { it.value == countOfPersons }.count()
                map = mutableMapOf<Char, Int>()
                countOfPersons = 0
                continue
            }

            countOfPersons++

            it.forEach { itt ->
                map[itt] = map.getOrDefault(itt, 0) + 1
            }
        }

        sum += map.filter { it.value == countOfPersons }.count()

        println(sum)
    }

    fun firstPart() {
        val inputs = Utils().parseLines("06");

        var sum = 0
        var map = mutableMapOf<Char, Int>()

        for (it in inputs) {
            if (it.isEmpty()) {
                println(map)
                println(map.count())
                sum += map.count()
                map = mutableMapOf<Char, Int>()
                continue
            }

            it.forEach { itt ->
                map[itt] = map.getOrDefault(itt, 0) + 1
            }
        }

        sum += map.count()

        println(sum)
    }
}