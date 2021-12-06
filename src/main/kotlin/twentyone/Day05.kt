package twentyone

import kotlin.math.absoluteValue

class Day05 {
    var maxX: Int = 0
    var maxY: Int = 0

    init {
        partOne()
        partTwo()
    }

    fun partTwo() {
        val inputs = Utils().parseLines("05").toList()

        val lines = inputs.map { parseLine(it) }
        lines.forEach {
            if ((it.maximumX()+1) > maxX) {
                maxX = (it.maximumX()+1)
            }

            if ((it.maximumY()+1) > maxY) {
                maxY = (it.maximumY()+1)
            }
        }

        var desk = arrayOfNulls<IntArray>(maxY)
        desk.forEachIndexed { index, ints ->
            if (ints == null) {
                val arr = IntArray(maxX)
                arr.fill(0, 0, maxX)

                desk[index] = arr
            }
        }

        lines.forEach {
            if (it.isLine()) {
                if (it.x1 == it.x2) {
                    if (it.y1 > it.y2) {
                        markArrayY(it.y2, it.y1, it.x1, desk)
                    } else {
                        markArrayY(it.y1, it.y2, it.x1, desk)
                    }
                }

                if (it.y1 == it.y2) {
                    if (it.x1 > it.x2) {
                        markArrayX(it.x2, it.x1, it.y1, desk)
                    } else {
                        markArrayX(it.x1, it.x2, it.y1, desk)
                    }
                }
            }

            if (it.isDiagonal()) {
                var deltaX = 0
                var deltaY = 0

                if (it.x1 > it.x2 && it.y1 > it.y2) {
                    deltaX = -1
                    deltaY = -1
                } else if (it.x1 > it.x2 && it.y1 < it.y2) {
                    deltaX = -1
                    deltaY = 1
                } else if (it.x1 < it.x2 && it.y1 > it.y2) {
                    deltaX = 1
                    deltaY = -1
                } else if (it.x1 < it.x2 && it.y1 < it.y2) {
                    deltaX = 1
                    deltaY = 1
                }

                var x = it.x1
                var y = it.y1
                markArrayX(x, x, y, desk)

                do {
                    x += deltaX
                    y += deltaY
                    markArrayX(x, x, y, desk)
                } while (x != it.x2 && y != it.y2)
            }
        }

        var count = 0

        desk.forEachIndexed { index, ints ->
            ints?.forEach {
                if (it >= 2) {
                    count++
                }
            }
        }

        println(count)
    }

    fun partOne() {
        val inputs = Utils().parseLines("05").toList()

        val lines = inputs.map { parseLine(it) }
        lines.forEach {
            if ((it.maximumX()+1) > maxX) {
                maxX = (it.maximumX()+1)
            }

            if ((it.maximumY()+1) > maxY) {
                maxY = (it.maximumY()+1)
            }
        }

        var desk = arrayOfNulls<IntArray>(maxY)
        desk.forEachIndexed { index, ints ->
            if (ints == null) {
                val arr = IntArray(maxX)
                arr.fill(0, 0, maxX)

                desk[index] = arr
            }
        }

        lines.forEach {
            if (it.isLine()) {
                if (it.x1 == it.x2) {
                    if (it.y1 > it.y2) {
                        markArrayY(it.y2, it.y1, it.x1, desk)
                    } else {
                        markArrayY(it.y1, it.y2, it.x1, desk)
                    }
                }

                if (it.y1 == it.y2) {
                    if (it.x1 > it.x2) {
                        markArrayX(it.x2, it.x1, it.y1, desk)
                    } else {
                        markArrayX(it.x1, it.x2, it.y1, desk)
                    }
                }
            }
        }

        var count = 0

        desk.forEachIndexed { index, ints ->
            ints?.forEach {
                if (it >= 2) {
                    count++
                }
            }
        }

        println(count)
    }

    fun markArrayY(y1: Int, y2: Int, x: Int, desk: Array<IntArray?>) {
        var y = y1
        while(y <= y2) {
            desk[y]?.set(x, desk[y]?.get(x)!! + 1)
            y++
        }
    }

    fun markArrayX(x1: Int, x2: Int, y: Int, desk: Array<IntArray?>) {
        var x = x1
        while(x <= x2) {
            desk[y]?.set(x, desk[y]?.get(x)!! + 1)
            x++
        }
    }

    fun parseLine(str: String): line {
        val parts = str.split(" -> ")
        val coordinates = mutableListOf<Int>()

        coordinates.addAll(parts.get(0).split(',').map { Integer.parseInt(it) })
        coordinates.addAll(parts.get(1).split(',').map { Integer.parseInt(it) })

        return line(coordinates.get(0), coordinates.get(1), coordinates.get(2), coordinates.get(3))
    }

    class line {
        var x1: Int
        var y1: Int
        var x2: Int
        var y2: Int

        constructor(x1: Int, y1: Int, x2: Int, y2: Int) {
            this.x1 = x1
            this.y1 = y1
            this.x2 = x2
            this.y2 = y2
        }

        fun isLine(): Boolean {
            return x1 == x2 || y1 == y2
        }

        fun isDiagonal(): Boolean {
            return (x1 - x2).absoluteValue == (y1 - y2).absoluteValue
        }

        fun maximumX(): Int {
            return Integer.max(this.x1, this.x2)
        }

        fun maximumY(): Int {
            return Integer.max(this.y1, this.y2)
        }
    }
}