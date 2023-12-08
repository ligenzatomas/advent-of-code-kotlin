package twentythree

class Day02 {

    init {
        firstPart()
        secondPart()
    }

    // 2149
    private fun firstPart() {
        println( parseGames(Utils().parseLines("02"), 12, 14, 13)
            .sumBy { if (it.isPlayable) it.id else 0 })
    }

    // 71274
    private fun secondPart() {
        val inputs = Utils().parseLines("02")
        val result = inputs.map {
            val gameId = it.substring(0, it.indexOf(":")).replace("Game ", "").toInt()
            val gameSets = it.substring(it.indexOf(":")+1).split(";").map {
                it.split(",").map {
                    Pair(it.trim().split(" ").last(), it.trim().split(" ").first().toInt())
                }
            }

            Game2(
                gameId,
                gameSets
                    .maxOf {
                        it.filter { it.first == "red" }.getOrElse(0, { Pair("red", 0) }).second
                    },
                gameSets
                    .maxOf {
                        it.filter { it.first == "blue" }.getOrElse(0, { Pair("blue", 0) }).second
                   },
                gameSets
                    .maxOf {
                        it.filter { it.first == "green" }.getOrElse(0, { Pair("green", 0) }).second
                    }
            )
        }

        println(result.sumOf { it.redMax * it.blueMax * it.greenMax })
    }

    private fun parseGames(inputs: List<String>, redMax: Int, blueMax: Int, greenMax: Int): List<Game> {
        return inputs.map {
            val gameId = it.substring(0, it.indexOf(":")).replace("Game ", "").toInt()
            val gameSets = it.substring(it.indexOf(":")+1).split(";").map {
                it.split(",").map {
                    Pair(it.trim().split(" ").last(), it.trim().split(" ").first().toInt())
                }
            }

            Game(
                gameId,
                gameSets
                    .all {
                        it.all {
                            when (it.first) {
                                "red" -> it.second <= redMax
                                "blue" -> it.second <= blueMax
                                "green" -> it.second <= greenMax
                                else -> false
                            }
                        }
                    }
            )
        }
    }

    data class Game(val id: Int, val isPlayable: Boolean)

    data class Game2(val id: Int, val redMax: Int, val blueMax: Int, val greenMax: Int)
}