package twentyone

import java.math.BigInteger

class Day06 {
    init {
        partOne()
        partTwo()
    }


    fun partTwo() {
        val state = state(Utils().parseLines("06").toList().get(0).split(',').map { Integer.parseInt(it) }.toList())

        var days = 1

        while (days <= 256) {
            state.runDay()
            days++
        }

        println(state.count())
    }

    fun partOne() {
        val inputs = Utils().parseLines("06").toList().get(0).split(',').map { Integer.parseInt(it) }.map { fish(it) }.toMutableList()

        var days = 1

        while (days <= 80) {
            var newOnes = inputs.filter { it.counter == 0 }.size

            inputs.forEach {
                if (it.counter == 0) {
                    it.counter = 6
                } else {
                    it.counter--
                }
            }

            while (newOnes > 0) {
                inputs.add(fish())

                newOnes--
            }

            days++
        }

        println(inputs.size)
    }

    class state(line: List<Int>) {
        var eight: BigInteger = BigInteger.ZERO
        var seven: BigInteger = BigInteger.ZERO
        var six: BigInteger = BigInteger.ZERO
        var five: BigInteger = BigInteger.ZERO
        var four: BigInteger = BigInteger.ZERO
        var three: BigInteger = BigInteger.ZERO
        var two: BigInteger = BigInteger.ZERO
        var one: BigInteger = BigInteger.ZERO
        var zero: BigInteger = BigInteger.ZERO

        init {
            line.forEach {
                if (it == 1) {
                    one++
                } else if (it == 2) {
                    two++
                } else if (it == 3) {
                    three++
                } else if (it == 4) {
                    four++
                } else if (it == 5) {
                    five++
                }
            }
        }

        fun count(): String {
            return eight.plus(seven).plus(six).plus(five).plus(four).plus(three).plus(two).plus(one).plus(zero).toString()
        }

        fun runDay() {
            var newEight = zero
            var newSeven = eight
            var newSix = seven
            var newFive = six
            var newFour = five
            var newThree = four
            var newTwo = three
            var newOne = two
            var newZero = one

            newSix += zero

            eight = newEight
            seven = newSeven
            six = newSix
            five = newFive
            four = newFour
            three = newThree
            two = newTwo
            one = newOne
            zero = newZero
        }
    }

    class fish {
        var counter: Int

        constructor(age: Int = 8) {
            this.counter = age
        }
    }
}