package twentyone

class Day09 {
    init {
        //partOne()
        partTwo()
    }

    fun partTwo() {
        val inputs = Utils().parseLines("09").toList().map { it.toCharArray() }.toList()
        var basins = mutableListOf<Int>()

        inputs.forEachIndexed { indexChars, chars ->
            chars.forEachIndexed { indexChar, char ->
                if (isLowest(char, indexChar, indexChars, inputs)) {
                    basins.add(countBasinSize(indexChar, indexChars, inputs, mutableListOf()))
                }
            }
        }

        basins.sort()

        println(basins[basins.size-1].toString() + " " + basins[basins.size-2] + " " + basins[basins.size-3])
        println(basins[basins.size-1]*basins[basins.size-2]*basins[basins.size-3])
    }

    data class Point(val x: Int, val y: Int)

    fun countBasinSize(x: Int, y: Int, inputs: List<CharArray>, countedPoints: MutableList<Point>): Int {
        var result = 1
        val intChar = Integer.parseInt(inputs[y][x].toString())
        countedPoints.add(Point(x, y))

        if (x > 0 && Integer.parseInt(inputs[y][x-1].toString()) > intChar && Integer.parseInt(inputs[y][x-1].toString()) < 9
            && !countedPoints.contains(Point(x-1, y))
        ) {
            result += countBasinSize(x-1, y, inputs, countedPoints)
        }

        if (x < (inputs[y].size-1) && Integer.parseInt(inputs[y][x+1].toString()) > intChar && Integer.parseInt(inputs[y][x+1].toString()) < 9
            && !countedPoints.contains(Point(x+1, y))
        ) {
            result += countBasinSize(x+1, y, inputs, countedPoints)
        }

        if (y > 0 && Integer.parseInt(inputs[y-1][x].toString()) > intChar && Integer.parseInt(inputs[y-1][x].toString()) < 9
            && !countedPoints.contains(Point(x, y-1))
        ) {
            result += countBasinSize(x, y-1, inputs, countedPoints)
        }

        if (y < (inputs.size-1) && Integer.parseInt(inputs[y+1][x].toString()) > intChar && Integer.parseInt(inputs[y+1][x].toString()) < 9
            && !countedPoints.contains(Point(x, y+1))
        ) {
            result += countBasinSize(x, y+1, inputs, countedPoints)
        }

        return result
    }

    fun partOne() {
        val inputs = Utils().parseLines("09").toList().map { it.toCharArray() }.toList()
        var result = 0

        inputs.forEachIndexed { indexChars, chars ->
            chars.forEachIndexed { indexChar, char ->
                if (isLowest(char, indexChar, indexChars, inputs)) {
                    result += 1 + Integer.parseInt(char.toString())
                }
            }
        }

        println(result)
    }

    fun isLowest(char: Char, x: Int, y: Int, inputs: List<CharArray>): Boolean {
        val intChar = Integer.parseInt(char.toString())

        if (x > 0 && Integer.parseInt(inputs[y][x-1].toString()) <= intChar) {
            return false
        }

        if (x < (inputs[y].size-1) && Integer.parseInt(inputs[y][x+1].toString()) <= intChar) {
            return false
        }

        if (y > 0 && Integer.parseInt(inputs[y-1][x].toString()) <= intChar) {
            return false
        }

        if (y < (inputs.size-1) && Integer.parseInt(inputs[y+1][x].toString()) <= intChar) {
            return false
        }

        return true
    }
}