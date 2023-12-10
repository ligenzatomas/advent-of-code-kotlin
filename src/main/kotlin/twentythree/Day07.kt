package twentythree

class Day07 {
    init {
        firstPart()
    }

    // 248475413 too low
    // 251524087 too low
    // 251927063 right
    private fun firstPart() {
        val map = Utils().parseLines("07").map { line ->
            val parts = line.split(" ")
            val bid = parts[1].toInt()
            val cards = parts[0].map { Card(it) }

            Hand(cards, bid)
        }.sorted().reversed()

        val results = map.mapIndexed { index, hand ->
            (index + 1) * hand.bid
        }

        println( results.sum() )
    }

    data class Card(val value: Char) {
        fun value(): Int {
            return when (value) {
                'A' -> 14
                'K' -> 13
                'Q' -> 12
                'J' -> 11
                'T' -> 10
                else -> value.toString().toInt()
            }
        }
    }

    data class Hand(val cards: List<Card>, val bid: Int) : Comparable<Hand> {
        fun isFiveOfAKind(): Boolean {
            return cards.groupBy { it.value }.values.any { it.size == 5 }
        }

        fun isFourOfAKind(): Boolean {
            return cards.groupBy { it.value }.values.any { it.size == 4 }
        }

        fun isFullHouse(): Boolean {
            return cards.groupBy { it.value }.values.any { it.size == 3 } && cards.groupBy { it.value }.values.any { it.size == 2 }
        }

        fun isThreeOfAKind(): Boolean {
            return cards.groupBy { it.value }.values.any { it.size == 3 }
        }

        fun isTwoPairs(): Boolean {
            return cards.groupBy { it.value }.values.filter { it.size == 2 }.size == 2
        }

        fun isOnePair(): Boolean {
            return cards.groupBy { it.value }.values.any { it.size == 2 }
        }

        fun isHighCard(): Boolean {
            return cards.groupBy { it.value }.values.all { it.size == 1 }
        }

        fun handType(): Int {
            return when {
                isFiveOfAKind() -> 1
                isFourOfAKind() -> 2
                isFullHouse() -> 3
                isThreeOfAKind() -> 4
                isTwoPairs() -> 5
                isOnePair() -> 6
                isHighCard() -> 7
                else -> throw Exception("Unknown hand type")
            }
        }

        fun compareToOtherHand(other: Hand): Int {
            return when {
                handType() < other.handType() -> -1
                handType() > other.handType() -> 1
                else -> cards.zip(other.cards).map { when {
                    it.first.value() < it.second.value() -> 1
                    it.first.value() > it.second.value() -> -1
                    else -> 0
                } }.first { it != 0 }
            }
        }

        override fun compareTo(other: Hand): Int {
            return compareToOtherHand(other)
        }
    }
}