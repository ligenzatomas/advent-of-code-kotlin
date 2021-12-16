package twentyone

class Day13 {
    init {
        //partOne()
        partTwo()
    }

    // LKREBPRK
    fun partTwo() {
        val inputs = Utils().parseLines("13").toList()

        var coordinates = inputs.filter { it.contains(',') }.map {
            Coordinate(Integer.parseInt(it.split(',')[0]), Integer.parseInt(it.split(',')[1]))
        }

        val actions = inputs.filter { it.contains("fold along ") }.map { Action(it.substring(it.indexOf('=')-1, it.indexOf('='))[0], Integer.parseInt(it.substring(it.indexOf('=')+1, it.length))) }

        var result: Set<Coordinate> = coordinates.toSet()

        actions.forEach {
            result = result.mapNotNull { coordinate -> it.fold(coordinate) }.toSet()
        }

        for (y: Int in (0..result.maxOf { it.y })) {
            for (x: Int in (0..result.maxOf { it.x })) {
                if (result.any { it.x == x && it.y == y }) {
                    print('*')
                } else {
                    print('.')
                }
            }

            println()
        }
    }

    fun partOne() {
        val inputs = Utils().parseLines("13").toList()

        val coordinates = inputs.filter { it.contains(',') }.map {
            Coordinate(Integer.parseInt(it.split(',')[0]), Integer.parseInt(it.split(',')[1]))
        }

        val actions = inputs.filter { it.contains("fold along ") }.map { Action(it.substring(it.indexOf('=')-1, it.indexOf('='))[0], Integer.parseInt(it.substring(it.indexOf('=')+1, it.length))) }

        println(coordinates.mapNotNull { actions[0].fold(it) }.toSet().size)
    }

    data class Coordinate(var x: Int, var y: Int)

    data class Action(val osa: Char, val delta: Int) {
        fun fold(coordinate: Coordinate): Coordinate? {
            var y: Int? = null
            var x: Int? = null

            if (osa == 'y') {
                if (coordinate.y - delta > 0) {
                    y = delta - (coordinate.y - delta)
                } else if (coordinate.y - delta < 0) {
                    y = coordinate.y
                }

                x = coordinate.x
            }

            if (osa == 'x') {
                if (coordinate.x - delta > 0) {
                    x = delta - (coordinate.x - delta)
                } else if (coordinate.x - delta < 0) {
                    x = coordinate.x
                }

                y = coordinate.y
            }

            if ( y != null && x != null) {
                return Coordinate(x, y)
            }

            return null
        }
    }
}