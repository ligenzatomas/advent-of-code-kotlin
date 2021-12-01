package twenty

class Day11 {
    constructor() {
        secondPart()
    }

    // 2607 too high
    fun secondPart() {
        val inputs = Utils().parseLines("11").toMutableList()
        size = inputs[0].length
        length = inputs.size

        var first = Array(length) { Array(size) { 'x' } }
        for (i in inputs.indices) {
            var index = 0
            for (j in inputs[i]) {
                first[i][index] = j
                index++
            }
        }

        //printArray(first)

        while (changes > 0) {
            val arr = runFirst2(first)
            //printArray(arr)
            changes = 0
            first = runSecond2(arr)
            //printArray(first)
        }

        //printArray(first)
        countAllOccupied(first)
    }

    fun runFirst2(arr4: Array<Array<Char>>): Array<Array<Char>> {
        val arr5 = Array(length) { Array(size) { 'x' } }

        for (i in arr4.indices) {
            for ((index, j) in arr4[i].withIndex()) {
                arr5[i][index] = firstRule2(j, i, index, arr4)
            }
        }

        println(changes)
        changes = 0

        return arr5
    }

    fun runSecond2(arr4: Array<Array<Char>>): Array<Array<Char>> {
        val arr5 = Array(length) { Array(size) { 'x' } }

        for (i in arr4.indices) {
            for ((index, j) in arr4[i].withIndex()) {
                arr5[i][index] = secondRule2(j, i, index, arr4)
            }
        }

        println(changes)

        return arr5
    }

    fun secondRule2(ch: Char, row: Int, col: Int, inputs: Array<Array<Char>>): Char {
        if (ch == '#') {
            var count = 0
            if (row - 1 >= 0) {
                if (col - 1 >= 0) {
                    count += countOccupied2(row - 1, col - 1, inputs, { x: Int -> x - 1 }, { y: Int -> y - 1 })
                }

                count += countOccupied2(row - 1, col, inputs, { x: Int -> x - 1 }, { y: Int -> y })

                if (col + 1 < size) {
                    count += countOccupied2(row - 1, col + 1, inputs, { x: Int -> x - 1 }, { y: Int -> y + 1 })
                }
            }

            if (col - 1 >= 0) {
                count += countOccupied2(row, col - 1, inputs, { x: Int -> x }, { y: Int -> y - 1 })
            }

            if (col + 1 < size) {
                count += countOccupied2(row, col + 1, inputs, { x: Int -> x }, { y: Int -> y + 1 })
            }

            if (row + 1 < length) {
                if (col - 1 >= 0) {
                    count += countOccupied2(row + 1, col - 1, inputs, { x: Int -> x + 1 }, { y: Int -> y - 1 })
                }

                count += countOccupied2(row + 1, col, inputs, { x: Int -> x + 1 }, { y: Int -> y })

                if (col + 1 < size) {
                    count += countOccupied2(row + 1, col + 1, inputs, { x: Int -> x + 1 }, { y: Int -> y + 1 })
                }
            }

            /*if (row == 0 && col == 3) {
                println("11111111111111111111111111111")
                println(countOccupied2(row, col - 1, inputs, { x: Int -> x }, { y: Int -> y - 1 }))
                println(countOccupied2(row, col + 1, inputs, { x: Int -> x }, { y: Int -> y + 1 }))
                println(count)
            }*/

            if (count >= 5) {
                changes++
                return 'L'
            }
        }

        return ch
    }

    fun firstRule2(ch: Char, row: Int, col: Int, inputs: Array<Array<Char>>): Char {
        if (ch == 'L') {
            var count = 0
            if (row - 1 >= 0) {
                if (col - 1 >= 0) {
                    count += countEmpty2(row - 1, col - 1, inputs, { x: Int -> x - 1 }, { y: Int -> y - 1 })
                }

                count += countEmpty2(row - 1, col, inputs, { x: Int -> x - 1 }, { y: Int -> y })

                if (col + 1 < size) {
                    count += countEmpty2(row - 1, col + 1, inputs, { x: Int -> x - 1 }, { y: Int -> y + 1 })
                }
            }

            if (col - 1 >= 0) {
                count += countEmpty2(row, col - 1, inputs, { x: Int -> x }, { y: Int -> y - 1 })
            }

            if (col + 1 < size) {
                count += countEmpty2(row, col + 1, inputs, { x: Int -> x }, { y: Int -> y + 1 })
            }

            if (row + 1 < length) {
                if (col - 1 >= 0) {
                    count += countEmpty2(row + 1, col - 1, inputs, { x: Int -> x + 1 }, { y: Int -> y - 1 })
                }

                count += countEmpty2(row + 1, col, inputs, { x: Int -> x + 1 }, { y: Int -> y })

                if (col + 1 < size) {
                    count += countEmpty2(row + 1, col + 1, inputs, { x: Int -> x + 1 }, { y: Int -> y + 1 })
                }
            }

            if (count == 0) {
                changes++
                return '#'
            }
        }

        return ch
    }

    fun countEmpty2(row: Int, col: Int, inputs: Array<Array<Char>>, xlambda: (Int) -> Int, ylambda: (Int) -> Int): Int {
        var x = row
        var y = col
        while (x < length && x >= 0 && y < size && y >= 0) {
            if (inputs[x].get(y) != '.') {
                if (inputs[x].get(y) == 'L') {
                    return 0
                }

                if (inputs[x].get(y) == '#') {
                    return 1
                }
            }
            x = xlambda(x)
            y = ylambda(y)
        }

        return 0
    }

    fun countOccupied2(row: Int, col: Int, inputs: Array<Array<Char>>, xlambda: (Int) -> Int, ylambda: (Int) -> Int): Int {
        var x = row
        var y = col
        while (x < length && x >= 0 && y < size && y >= 0) {
            if (inputs[x].get(y) != '.') {
                if (inputs[x].get(y) == 'L') {
                    return 0
                }

                if (inputs[x].get(y) == '#') {
                    return 1
                }
            }
            x = xlambda(x)
            y = ylambda(y)
        }

        return 0
    }

    var size = 0
    var length = 0
    var changes = 1

    // 2263
    fun firstPart() {
        val inputs = Utils().parseLines("11").toMutableList()
        size = inputs[0].length
        length = inputs.size

        var first = Array(length) { Array(size) { 'x' } }
        for (i in inputs.indices) {
            var index = 0
            for (j in inputs[i]) {
                first[i][index] = j
                index++
            }
        }

        while (changes > 0) {
            val arr = runFirst(first)
            changes = 0
            first = runSecond(arr)
        }

        printArray(first)
        countAllOccupied(first)
    }

    fun countAllOccupied(arr: Array<Array<Char>>) {
        var count = 0

        for (i in arr.indices) {
            for ((index, j) in arr[i].withIndex()) {
                count += countOccupied(i, index, arr)
            }
        }

        println(count)
    }

    fun runFirst(arr4: Array<Array<Char>>): Array<Array<Char>> {
        val arr5 = Array(length) { Array(size) { 'x' } }

        for (i in arr4.indices) {
            for ((index, j) in arr4[i].withIndex()) {
                arr5[i][index] = firstRule(j, i, index, arr4)
            }
        }

        println(changes)
        changes = 0

        return arr5
    }

    fun runSecond(arr4: Array<Array<Char>>): Array<Array<Char>> {
        val arr5 = Array(length) { Array(size) { 'x' } }

        for (i in arr4.indices) {
            for ((index, j) in arr4[i].withIndex()) {
                arr5[i][index] = secondRule(j, i, index, arr4)
            }
        }

        println(changes)

        return arr5
    }

    fun secondRule(ch: Char, row: Int, col: Int, inputs: Array<Array<Char>>): Char {
        if (ch == '#') {
            var count = 0
            if (row - 1 >= 0) {
                if (col - 1 >= 0) {
                    count += countOccupied(row - 1, col - 1, inputs)
                }

                count += countOccupied(row - 1, col, inputs)

                if (col + 1 < size) {
                    count += countOccupied(row - 1, col + 1, inputs)
                }
            }

            if (col - 1 >= 0) {
                count += countOccupied(row, col - 1, inputs)
            }

            if (col + 1 < size) {
                count += countOccupied(row, col + 1, inputs)
            }

            if (row + 1 < length) {
                if (col - 1 >= 0) {
                    count += countOccupied(row + 1, col - 1, inputs)
                }

                count += countOccupied(row + 1, col, inputs)

                if (col + 1 < size) {
                    count += countOccupied(row + 1, col + 1, inputs)
                }
            }

            if (count >= 4) {
                changes++
                return 'L'
            }
        }

        return ch
    }

    fun printArray(arr: Array<Array<Char>>) {
        for (i in arr) {
            var s = ""
            for (j in i) {
                s += j.toString()
            }
            println(s)
        }
    }

    fun firstRule(ch: Char, row: Int, col: Int, inputs: Array<Array<Char>>): Char {
        if (ch == 'L') {
            var count = 0
            if (row - 1 >= 0) {
                if (col - 1 >= 0) {
                    count += countEmpty(row - 1, col - 1, inputs)
                }

                count += countEmpty(row - 1, col, inputs)

                if (col + 1 < size) {
                    count += countEmpty(row - 1, col + 1, inputs)
                }
            }

            if (col - 1 >= 0) {
                count += countEmpty(row, col - 1, inputs)
            }

            if (col + 1 < size) {
                count += countEmpty(row, col + 1, inputs)
            }

            if (row + 1 < length) {
                if (col - 1 >= 0) {
                    count += countEmpty(row + 1, col - 1, inputs)
                }

                count += countEmpty(row + 1, col, inputs)

                if (col + 1 < size) {
                    count += countEmpty(row + 1, col + 1, inputs)
                }
            }

            if (count == 0) {
                changes++
                return '#'
            }
        }

        return ch
    }

    fun countEmpty(row: Int, col: Int, inputs: Array<Array<Char>>): Int {
        if (inputs[row].get(col) != '#') {
            return 0
        }

        return 1
    }

    fun countOccupied(row: Int, col: Int, inputs: Array<Array<Char>>): Int {
        if (inputs[row].get(col) == '#') {
            return 1
        }

        return 0
    }
}