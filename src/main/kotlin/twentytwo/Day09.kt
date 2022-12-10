package twentytwo

import twentytwo.Day09.Direction.*
import kotlin.math.abs

class Day09 {
    init {
        firstPart()
        secondPart()
    }

    // 5960
    private fun firstPart() {
        val inputs = Utils().parseLines("09")

        var head = Point(0, 0)
        var tail = Point(0, 0)
        val positions = mutableSetOf<Point>()

        positions.add(tail)

        inputs.forEach {
            val direction = valueOf(it[0].toString())
            val steps = it.substring(2, it.length).toInt()

            println("Move ${direction} $steps")

            for (i in 0 until steps) {
                when (direction) {
                    L -> head = Point(head.x-1, head.y)
                    R -> head = Point(head.x+1, head.y)
                    U -> head = Point(head.x, head.y+1)
                    D -> head = Point(head.x, head.y-1)
                }

                // they are in the same row or column
                if (head.x == tail.x || head.y == tail.y) {
                    if (head.x > tail.x && head.x - tail.x > 1) {
                        tail = Point(tail.x + 1, tail.y)
                    } else if (head.x < tail.x && tail.x - head.x > 1) {
                        tail = Point(tail.x - 1, tail.y)
                    } else if (head.y > tail.y && head.y - tail.y > 1) {
                        tail = Point(tail.x, tail.y + 1)
                    } else if (head.y < tail.y && tail.y - head.y > 1) {
                        tail = Point(tail.x, tail.y - 1)
                    }
                } else if (abs(head.x - tail.x) * abs(head.y - tail.y) >= 2) {
                    if (head.x > tail.x && head.x - tail.x > 1) {
                        if (head.y > tail.y) {
                            tail = Point(tail.x + 1, tail.y + 1)
                        } else {
                            tail = Point(tail.x + 1, tail.y - 1)
                        }
                    } else if (head.x < tail.x && tail.x - head.x > 1) {
                        if (head.y > tail.y) {
                            tail = Point(tail.x - 1, tail.y + 1)
                        } else {
                            tail = Point(tail.x - 1, tail.y - 1)
                        }
                    } else if (head.y > tail.y && head.y - tail.y > 1) {
                        if (head.x > tail.x) {
                            tail = Point(tail.x + 1, tail.y + 1)
                        } else {
                            tail = Point(tail.x - 1, tail.y + 1)
                        }
                    } else if (head.y < tail.y && tail.y - head.y > 1) {
                        if (head.x > tail.x) {
                            tail = Point(tail.x + 1, tail.y - 1)
                        } else {
                            tail = Point(tail.x - 1, tail.y - 1)
                        }
                    }
                }

                println("$head $tail")

                positions.add(tail)
            }

        }

        println(positions.size)
    }

    // 2327
    private fun secondPart() {
        val inputs = Utils().parseLines("09")

        val rope = mutableMapOf<Int, Point>()
        for (i in 0 until 10) {
            rope.put(i, Point(0, 0))
        }
        val positions = mutableSetOf<Point>()

        positions.add(Point(0, 0))

        inputs.forEach {
            val direction = valueOf(it[0].toString())
            val steps = it.substring(2, it.length).toInt()

            println("Move ${direction} $steps")

            for (i in 0 until steps) {
                val head = rope.get(0)!!

                when (direction) {
                    L -> rope.set(0, Point(head.x-1, head.y))
                    R -> rope.set(0, Point(head.x+1, head.y))
                    U -> rope.set(0, Point(head.x, head.y+1))
                    D -> rope.set(0, Point(head.x, head.y-1))
                }

                for (i in 1 until 10) {
                    val head = rope.get(i-1)!!
                    var tail = rope.get(i)!!

                    rope.set(i, moving(head, tail))
                }

                positions.add(rope.get(9)!!)
            }

        }

        println(positions.size)
    }

    private fun moving(head: Point, tail: Point): Point {
        if (head.x == tail.x || head.y == tail.y) {
            if (head.x > tail.x && head.x - tail.x > 1) {
                return Point(tail.x + 1, tail.y)
            } else if (head.x < tail.x && tail.x - head.x > 1) {
                return Point(tail.x - 1, tail.y)
            } else if (head.y > tail.y && head.y - tail.y > 1) {
                return Point(tail.x, tail.y + 1)
            } else if (head.y < tail.y && tail.y - head.y > 1) {
                return Point(tail.x, tail.y - 1)
            }
        } else if (abs(head.x - tail.x) * abs(head.y - tail.y) >= 2) {
            if (head.x > tail.x && head.x - tail.x > 1) {
                if (head.y > tail.y) {
                    return Point(tail.x + 1, tail.y + 1)
                } else {
                    return Point(tail.x + 1, tail.y - 1)
                }
            } else if (head.x < tail.x && tail.x - head.x > 1) {
                if (head.y > tail.y) {
                    return Point(tail.x - 1, tail.y + 1)
                } else {
                    return Point(tail.x - 1, tail.y - 1)
                }
            } else if (head.y > tail.y && head.y - tail.y > 1) {
                if (head.x > tail.x) {
                    return Point(tail.x + 1, tail.y + 1)
                } else {
                    return Point(tail.x - 1, tail.y + 1)
                }
            } else if (head.y < tail.y && tail.y - head.y > 1) {
                if (head.x > tail.x) {
                    return Point(tail.x + 1, tail.y - 1)
                } else {
                    return Point(tail.x - 1, tail.y - 1)
                }
            }
        }

        return Point(tail.x, tail.y)
    }

    data class Point(val x: Int, val y: Int)

    enum class Direction() {
        L, R, U, D
    }
}