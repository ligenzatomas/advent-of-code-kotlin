package twentytwo

class Day08 {
    init {
        secondPart()
    }

    // 1672 result
    private fun firstPart() {
        val inputs = Utils().parseLines("08").map { it.toCharArray() }.toTypedArray()
        var set = mutableSetOf<Pair<Int, Int>>()

        for (i in 0 until inputs.size) {
            if (i == 0 || i == inputs.size - 1) {
                IntRange(0, inputs.size - 1).forEach { set.add(Pair(it, i)) }
            } else {
                val list = mutableListOf<Int>()

                for (j in inputs[i].indices) {
                    if (j == inputs[i].size-1) {
                        list.clear()
                        break
                    } else if (j == 0) {
                        list.add(inputs[i][j].toInt())
                        continue
                    } else {
                        val value = inputs[i][j].toInt()
                        val otherMax = list.maxOrNull()!!

                        if (value > otherMax) {
                            set.add(Pair(i, j))
                        }

                        list.add(inputs[i][j].toInt())
                    }
                }

                for (j in inputs[i].size - 1 downTo 0) {
                    if (j == 0) {
                        break
                    } else if (j == inputs[i].size - 1) {
                        list.add(inputs[i][j].toInt())
                        continue
                    } else {
                        val value = inputs[i][j].toInt()
                        val otherMax = list.maxOrNull()!!

                        if (value > otherMax) {
                            set.add(Pair(i, j))
                        }

                        list.add(inputs[i][j].toInt())
                    }
                }
            }
        }

        for (x in 0 until inputs[0].size) {
            if (x == 0 || x == inputs[0].size - 1) {
                IntRange(1, inputs.size - 2).forEach { set.add(Pair(x, it)) }
            } else {
                val list = mutableListOf<Int>()

                for (y in inputs.indices) {
                    if (y == inputs.size - 1) {
                        list.clear()
                        break
                    } else if (y == 0) {
                        list.add(inputs[y][x].toInt())
                        continue
                    } else {
                        val value = inputs[y][x].toInt()
                        val otherMax = list.maxOrNull()!!

                        if (value > otherMax) {
                            set.add(Pair(y, x))
                        }

                        list.add(inputs[y][x].toInt())
                    }
                }

                for (y in inputs.size - 1 downTo 0) {
                    if (y == 0) {
                        break
                    } else if (y == inputs.size - 1) {
                        list.add(inputs[y][x].toInt())
                        continue
                    } else {
                        val value = inputs[y][x].toInt()
                        val otherMax = list.maxOrNull()!!

                        if (value > otherMax) {
                            set.add(Pair(y, x))
                        }

                        list.add(inputs[y][x].toInt())
                    }
                }
            }
        }

        println(set.size)
    }

    private fun secondPart() {
        val inputs = Utils().parseLines("08").map { it.toCharArray() }.toTypedArray()
        var highest = 0

        for (y in 1 until inputs.size - 1 ) {
            for (x in 1 until inputs[y].size - 1) {
                val value = measure(inputs[y][x].toInt(), y, x, inputs)

                if (value > highest) {
                    highest = value
                }
            }
        }

        println(highest)
    }

    private fun measure(actual: Int, y: Int, x: Int, inputs: Array<CharArray>): Int {
        // left
        var left = 0
        for (i in x-1 downTo  0) {
            left++
            if (actual <= inputs[y][i].toInt()) {
                break
            }
        }

        // right
        var right = 0
        for (i in x+1 until inputs[y].size) {
            right++
            if (actual <= inputs[y][i].toInt()) {
                break
            }
        }

        // up
        var up = 0
        for (i in y-1 downTo 0) {
            up++
            if (actual <= inputs[i][x].toInt()) {
                break
            }
        }

        // down
        var down = 0
        for (i in y+1 until inputs.size) {
            down++
            if (actual <= inputs[i][x].toInt()) {
                break
            }
        }

        return left * right * up * down
    }
}