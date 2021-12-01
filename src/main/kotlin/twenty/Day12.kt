package twenty

import kotlin.math.absoluteValue
import kotlin.math.cos
import kotlin.math.sin

class Day12 {
    constructor() {
        secondPart()
    }

    var x = 0
    var y = 0
    var angle = 90

    var waypointToShipX = 10
    var waypointToShipY = 1

    var waypointX = 10
    var waypointY = 1

    fun secondPart() {
        val inputs = Utils().parseLines("12")

        inputs.forEach {
            //println(x.toString() + " " + y.toString())
            //println(waypointX.toString() + " " + waypointY.toString())
            //println(waypointToShipX.toString() + " " + waypointToShipY.toString())
            //println()
            command2(it)
        }

        println(x.toString() + " " + y.toString())
        println(x.absoluteValue + y.absoluteValue)
    }

    fun command2(str: String) {
        val matches = "^(N|S|E|W|R|L|F)(\\d*)$".toRegex().find(str)!!

        if (matches.groupValues[1] == "N") {
            northW(matches.groupValues[2].toInt())
        } else if (matches.groupValues[1] == "S") {
            southW(matches.groupValues[2].toInt())
        } else if (matches.groupValues[1] == "E") {
            eastW(matches.groupValues[2].toInt())
        } else if (matches.groupValues[1] == "W") {
            westW(matches.groupValues[2].toInt())
        } else if (matches.groupValues[1] == "R") {
            rightW(-matches.groupValues[2].toInt())
        } else if (matches.groupValues[1] == "L") {
            rightW(matches.groupValues[2].toInt())
        } else if (matches.groupValues[1] == "F") {
            forwardW(matches.groupValues[2].toInt())
        }
    }

    fun northW(number: Int) {
        waypointY += number
    }

    fun southW(number: Int) {
        waypointY -= number
    }

    fun eastW(number: Int) {
        waypointX += number
    }

    fun westW(number: Int) {
        waypointX -= number
    }

    fun rightW(number: Int) {
        waypointToShipX = waypointX - x
        waypointToShipY = waypointY - y

        val angle = Math.toRadians(number.toDouble());

        waypointX = (x + (waypointToShipX * cos(angle)) - (waypointToShipY * sin(angle))).toInt()
        waypointY = (y + (waypointToShipX * sin(angle)) + (waypointToShipY * cos(angle))).toInt()
    }

    fun forwardW(number: Int) {
        val difX = (waypointX - x) * number
        val difY = (waypointY - y) * number

        x += difX
        y += difY

        waypointX += difX
        waypointY += difY
    }

    // 1441
    fun firstPart() {
        val inputs = Utils().parseLines("12")

        inputs.forEach {
            command(it)
        }

        println(x.toString() + " " + y.toString())
        println(x.absoluteValue + y.absoluteValue)
    }

    fun command(str: String) {
        val matches = "^(N|S|E|W|R|L|F)(\\d*)$".toRegex().find(str)!!

        if (matches.groupValues[1] == "N") {
            north(matches.groupValues[2].toInt())
        } else if (matches.groupValues[1] == "S") {
            south(matches.groupValues[2].toInt())
        } else if (matches.groupValues[1] == "E") {
            east(matches.groupValues[2].toInt())
        } else if (matches.groupValues[1] == "W") {
            west(matches.groupValues[2].toInt())
        } else if (matches.groupValues[1] == "R") {
            right(matches.groupValues[2].toInt())
        } else if (matches.groupValues[1] == "L") {
            left(matches.groupValues[2].toInt())
        } else if (matches.groupValues[1] == "F") {
            forward(matches.groupValues[2].toInt())
        }
    }

    fun north(number: Int) {
        y += number
    }

    fun south(number: Int) {
        y -= number
    }

    fun east(number: Int) {
        x += number
    }

    fun west(number: Int) {
        x -= number
    }

    fun right(number: Int) {
        angle += number

        if (angle > 360) angle -= 360
        if (angle < 0) angle += 360
    }

    fun left(number: Int) {
        angle -= number

        if (angle > 360) angle -= 360
        if (angle < 0) angle += 360
    }

    fun forward(number: Int) {
        if (angle == 0 || angle == 360) {
            north(number)
        } else if (angle == 90) {
            east(number)
        } else if (angle == 180) {
            south(number)
        } else if (angle == 270) {
            west(number)
        } else {
            throw Exception("chyba $angle")
        }
    }
}