package twenty

class Day13 {
    constructor() {
        //partTwo()
        test()
    }

    /* returns x where (a * x) % b == 1 */
    fun multInv(a: Int, b: Int): Int {
        if (b == 1) return 1
        var aa = a
        var bb = b
        var x0 = 0
        var x1 = 1
        while (aa > 1) {
            val q = aa / bb
            var t = bb
            bb = aa % bb
            aa = t
            t = x0
            x0 = x1 - q * x0
            x1 = t
        }
        if (x1 < 0) x1 += b
        return x1
    }

    fun chineseRemainder(n: IntArray, a: IntArray): Int {
        val prod = n.fold(1) { acc, i -> acc * i }
        var sum = 0
        for (i in 0 until n.size) {
            val p = prod / n[i]
            sum += a[i] * multInv(p, n[i]) * p
        }
        return sum % prod
    }

    fun test() {
        val n = intArrayOf(17, 37, 439, 29, 13, 23, 787, 41, 19)
        val a = intArrayOf(0, 11, 17, 19, 30, 40, 48, 58, 67)
        println(chineseRemainder(n, a))
    }

    // 1467900241541773 to high
    // 42305986 to low
    // 504465000000000
    // 100000000000000
    // 11016000000000
    // 23608000000000
    fun partTwo() {
        val inputs = Utils().parseLines("13")

        val buses = inputs[1].split(',')
        val numbers = mutableListOf<Array<Int>>()

        var x = 0

        buses.forEach {
            if ("^\\d*$".toRegex().matches(it)) {
                numbers.add(arrayOf(x, "^(\\d*)$".toRegex().find(it)!!.groupValues[1]!!.toInt()))
            }

            x++
        }

        var max = numbers.maxOfOrNull { it[1] }
        var maxDelta = numbers.filter { it[1] == max }.first()[0]

        p(numbers)
        var y = max?.minus(maxDelta)!!.toLong()
        println(max.toString() + " " + maxDelta + " " + y)

        while (true) {
            y += max

            for (i in numbers.indices) {
                if ((y + numbers[i][0]) % numbers[i][1] != 0L) break

                if (i == (numbers.size - 1)) {
                    println(y)
                    return
                }
            }

            if (y % 1000000000L == 0L) println(y)
        }
    }

    fun p(arr: List<Array<Int>>) {
        arr.forEach {
            println(it[0].toString() + " " + it[1])
        }
    }

    fun partOne() {
        val inputs = Utils().parseLines("13")

        val earliestTimestamp = inputs[0].toInt()
        val buses = inputs[1].split(',').filter { it != "x" }.map { it.toInt() }.toList()

        var range = earliestTimestamp

        println(earliestTimestamp)
        println(buses)
        println(range)

        while (range < earliestTimestamp + buses.maxOrNull()!!) {
            for (i in buses.indices) {
                println(range.toString() + " " + buses[i] + " " + (range % buses[i]))
                if (range % buses[i] == 0) {
                    println(buses[i].toString() + " " + (range - earliestTimestamp))
                    println(buses[i] * (range - earliestTimestamp))
                    return
                }
            }
            range++
        }
    }
}