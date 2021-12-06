package twentyone

class Day04 {
    init {
        partTwo()
    }

    fun partTwo() {
        val inputs = Utils().parseLines("04").toList()

        val allBingos = inputs.get(0).split(',').map { Integer.parseInt(it) }
        val tables = parseInputsIntoFives(inputs)

        val bingos = arrayListOf<Int>()

        var mutableTables = mutableListOf<five>()
        mutableTables.addAll(tables)

        var lastFive = five()

        allBingos.forEach {
            if (mutableTables.isEmpty()) {
                return@forEach
            }

            bingos.add(it)

            mutableTables.removeIf {
                if (it.isWinner(bingos)) {
                    lastFive = it
                    return@removeIf true
                }
                return@removeIf false
            }
        }

        println(lastFive.winnerSum(bingos))
    }

    fun partOne() {
        val inputs = Utils().parseLines("04").toList()

        val allBingos = inputs.get(0).split(',').map { Integer.parseInt(it) }
        val tables = parseInputsIntoFives(inputs)

        val bingos = arrayListOf<Int>()

        allBingos.forEach {
            bingos.add(it)

            tables.forEach {
                if (it.isWinner(bingos)) {
                    println(it.winnerSum(bingos))
                    return
                }
            }
        }
    }

    fun parseInputsIntoFives(inputs: List<String>): List<five> {
        val result = mutableListOf<five>()
        var item = five()

        inputs.forEachIndexed { index, str ->
            if (index != 0 && index != 1) {
                if (str.isEmpty()) {
                    result.add(item)
                    item = five()
                } else {
                    item.value.add(str.split(' ').filter { it.isNotEmpty() }.map { Integer.parseInt(it.trim())}.toTypedArray())
                }
            }
        }

        if (!item.value.isEmpty()) {
            result.add(item)
        }

        return result
    }

    class five {
        var value = mutableListOf<Array<Int>>()

        fun winnerSum( bingos: ArrayList<Int>): Int {
            var sum = 0

            value.forEach {
                sum += getNotBingos(it, bingos).sum()
            }

            return sum.times(bingos.last())
        }

        fun getNotBingos(arr: Array<Int>, bingos: ArrayList<Int>): Array<Int> {
            return arr.filter { !bingos.contains(it) }.toTypedArray()
        }

        fun isWinner( bingos: ArrayList<Int>): Boolean {
            value.forEachIndexed { index, ints ->
                if (isArrayWinner(ints, bingos)) {
                    return true
                }

                if (isArrayWinner(value.map { it.get(index) }.toTypedArray(), bingos)) {
                    return true
                }
            }

            return false
        }

        fun isArrayWinner(arr: Array<Int>, bingos: ArrayList<Int>): Boolean {
            return arr.none { !bingos.contains(it) }
        }
    }
}