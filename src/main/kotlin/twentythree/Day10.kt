package twentythree

class Day10 {
    init {
        firstPart()
        secondPart()
    }

    fun firstPart() {
        val area = Utils().parseLines("10").map { it.toList() }
        var actualPoints1 = mutableListOf<Point>()
        var actualPoints2 = mutableListOf<Point>()

        area.forEachIndexed { index, chars ->
            chars.forEachIndexed { index2, c ->
                if (c == 'S') {
                    actualPoints1.add(Point(index2, index, c))
                    actualPoints2.add(Point(index2, index, c))
                }
            }
        }

        var points1 = findNextPointFromStart(area, actualPoints1.first())

        if (points1.size != 2) throw Exception("points1.size != 2")

        actualPoints1.add(points1.first())
        actualPoints2.add(points1.last())

        var size1 = 1
        var size2 = 1

        while (actualPoints1.last() != actualPoints2.last() && actualPoints1.last() != actualPoints2.get(actualPoints2.size - 2)) {
            actualPoints1.add(findNextPoint(area, actualPoints1.last(), actualPoints1.get(actualPoints1.size - 2)))
            actualPoints2.add(findNextPoint(area, actualPoints2.last(), actualPoints2.get(actualPoints2.size - 2)))

            size1++
            size2++
        }

        println(size1.toString() + " " + size2.toString())
    }

    fun findNextPoint(area: List<List<Char>>, actualPoint: Point, lastPoint: Point): Point {
        var result: Point? = null

        val way = listOf(
            Point(0, -1, '|'),
            Point(0, 1, '|'),
            Point(-1, 0, '-'),
            Point(1, 0, '-'),
            Point(0, -1, 'L'),
            Point(1, 0, 'L'),
            Point(0, -1, 'J'),
            Point(-1, 0, 'J'),
            Point(1, 0, 'F'),
            Point(0, 1, 'F'),
            Point(0, 1, '7'),
            Point(-1, 0, '7'),
        )

        way.filter { it.char == actualPoint.char }.forEach { surround ->
            val newPoint = Point(actualPoint.x + surround.x, actualPoint.y + surround.y, area[actualPoint.y + surround.y][actualPoint.x + surround.x])

            if (newPoint != lastPoint) {
                result = newPoint
            }
        }

        return result!!
    }

    fun findNextPointFromStart(area: List<List<Char>>, actualPoint: Point): MutableList<Point> {
        val points = mutableListOf<Point>()

        val surroundings = mapOf(
            Pair(Point(-1, 0, null), listOf('-', 'F', 'L')),
            Pair(Point(1, 0, null), listOf('-', 'J', '7')),
            Pair(Point(0, -1, null), listOf('|', 'F', '7')),
            Pair(Point(0, 1, null), listOf('|', 'L', 'J')),
        )

        surroundings.forEach { surround ->
            val newPoint = Point(actualPoint.x + surround.key.x, actualPoint.y + surround.key.y, area[actualPoint.y + surround.key.y][actualPoint.x + surround.key.x])

            if (surround.value.contains(area[newPoint.y][newPoint.x])) {
                points.add(newPoint)
            }
        }

        return points
    }

    fun secondPart() {
        val area = Utils().parseLines("10").map { it.toList() }
        var actualPoints1 = mutableListOf<Point>()
        var actualPoints2 = mutableListOf<Point>()

        area.forEachIndexed { index, chars ->
            chars.forEachIndexed { index2, c ->
                if (c == 'S') {
                    actualPoints1.add(Point(index2, index, c))
                    actualPoints2.add(Point(index2, index, c))
                }
            }
        }

        var points1 = findNextPointFromStart(area, actualPoints1.first())

        if (points1.size != 2) throw Exception("points1.size != 2")

        actualPoints1.add(points1.first())
        actualPoints2.add(points1.last())

        while (actualPoints1.last() != actualPoints2.last() && actualPoints1.last() != actualPoints2.get(actualPoints2.size - 2)) {
            actualPoints1.add(findNextPoint(area, actualPoints1.last(), actualPoints1.get(actualPoints1.size - 2)))
            actualPoints2.add(findNextPoint(area, actualPoints2.last(), actualPoints2.get(actualPoints2.size - 2)))
        }

       actualPoints1.addAll(actualPoints2.dropLast(1).reversed())

        var allPoints = mutableListOf<Point>()

        area.forEachIndexed { index, chars ->
            chars.forEachIndexed { index2, c ->
                allPoints.add(Point(index2, index, c))
            }
        }

        var inside = 0

        allPoints.forEach {
            if (!actualPoints1.contains(it) && isPointInsidePolygon(it, actualPoints1.toTypedArray())) {
                inside++
            }

        }

        // 7651 to high
        // 589
        println(inside)
    }

    fun isPointInsidePolygon(point: Point, polygon: Array<Point>): Boolean {
        var count = 0
        var j = polygon.lastIndex
        for (i in polygon.indices) {
            val pi = polygon[i]
            val pj = polygon[j]

            if (pi.y > point.y != pj.y > point.y) {
                val x = (pj.x - pi.x) * (point.y - pi.y) / (pj.y - pi.y) + pi.x
                if (point.x < x) {
                    count++
                }
            }
            j = i
        }
        return count % 2 != 0
    }

    data class Point(val x: Int, val y: Int, val char: Char?) : Comparable <Point> {
        override fun compareTo(other: Point): Int {
            return when {
                this.y < other.y -> -1
                this.y > other.y -> 1
                else -> {
                    when {
                        this.x < other.x -> -1
                        this.x > other.x -> 1
                        else -> 0
                    }
                }
            }
        }

    }
}