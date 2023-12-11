package twentythree

class Day11 {
    init {
        firstPart()
        secondPart()
    }

    // points size 449
    // pairs size 100576
    // 9691644 too low
    // 10044988 too high
    // 10042936 too high
    // 10033566 ok
    fun firstPart() {
        val array = Utils().parseLines("11").toMutableList()
        val firstSize = array.size
        val firstLength = array[0].length

        val emptyRows = array.mapIndexed { index: Int, s: String ->  Pair(index, s)} .filter { it.second.all { it == '.' } }
        emptyRows.forEachIndexed { i, pair ->
            array.add(pair.first + i, pair.second)
        }

        val emptyCols = (0 until array[0].length).filter { col ->
            (0 until array.size).all { row ->
                array[row][col] == '.'
            }
        }
        emptyCols.forEachIndexed { i, col ->
            array.forEachIndexed { index, s ->
                array[index] = s.substring(0, col+i) + "." + s.substring(col+i)
            }
        }

        val expandedArray = array

        println("firstSize " + firstSize + " firstLength " + firstLength + " expandedArray size " + expandedArray.size + " expandedArray length " + expandedArray[0].length)

        var count = 1
        val points = mutableListOf<Point>()

        expandedArray.forEachIndexed { index, s ->
            s.forEachIndexed { index2, c ->
                if (c == '#') {
                    points.add(Point(index2.toLong(), index.toLong(), count))
                    count++
                }
            }
        }

        println("points size " + points.size)

        val pairs = points.flatMapIndexed { index, point ->
            points.drop(index + 1).map { otherPoint -> Pair(point, otherPoint) }
        }

        println("pairs size " + pairs.size)

        val result = pairs.map {
            it.first.shortestPathTo(it.second)
        }.sum()

        println(result)
    }

    fun secondPart() {
        val array = Utils().parseLines("11").toMutableList()
        val number = 1000000

        val emptyRows = array.mapIndexed { index: Int, s: String ->  Pair(index, s)} .filter { it.second.all { it == '.' } }.map { it.first }

        val emptyCols = (0 until array[0].length).filter { col ->
            (0 until array.size).all { row ->
                array[row][col] == '.'
            }
        }

        var count = 1
        var addedRows = 0L
        val points = mutableListOf<Point>()

        array.forEachIndexed { index, s ->
            if (index in emptyRows) {
                addedRows += number-1
            }

            var addedCols = 0L

            s.forEachIndexed { index2, c ->
                if (index2 in emptyCols) {
                    addedCols += number-1
                }

                if (c == '#') {
                    points.add(Point(index2 + addedCols, index + addedRows, count))
                    count++
                }
            }
        }

        println("points size " + points.size)

        val pairs = points.flatMapIndexed { index, point ->
            points.drop(index + 1).map { otherPoint -> Pair(point, otherPoint) }
        }

        println("pairs size " + pairs.size)

        val result = pairs.map {
            it.first.shortestPathTo(it.second)
        }.sum()

        println(result)
    }

    data class Point(val x: Long, val y: Long, val c: Int) {
        fun shortestPathTo(point: Point): Long {
            return Math.abs(point.x - x) + Math.abs(point.y - y)
        }
    }
}