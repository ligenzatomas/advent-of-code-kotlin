package twentythree

class Day07b {
    init {
        secondPart()
    }

    // 256141380 too high
    // 255596089 too low
    // 255633199 too high
    // 255425653 not right
    // 256007911 not right
    // 255429684
    // 255632664 should be ok
    // 255632664
    private fun secondPart() {
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
                'J' -> 1
                'T' -> 10
                else -> value.toString().toInt()
            }
        }
    }

    data class Hand(val cards: List<Card>, val bid: Int) : Comparable<Hand> {

        fun jokersCount(): Int {
            return cards.count { it.value == 'J' }
        }
        fun isFiveOfAKind(): Boolean {
            val jokers = jokersCount()

            if (jokers == 5) return true

            val groups = cards.filter { it.value != 'J' }.groupBy { it.value }.values

            return groups.any { group -> group.size + jokers >= 5 }
        }

        fun isFourOfAKind(): Boolean {
            val jokers = jokersCount()

            if (jokers == 4) return true

            val groups = cards.filter { it.value != 'J' }.groupBy { it.value }.values

            return groups.any { group -> group.size + jokers >= 4 }
        }

        fun isFullHouse(): Boolean {
            val jokers = jokersCount()
            val groups = cards.groupBy { it.value }.values

            if (cards.filter { it.value != 'J' }.groupBy { it.value }.size > 2) {
                return false
            }

            if (groups.any { it.size == 3 } && groups.any { it.size == 2 }) {
                return true
            }

            if (jokers > 0) {
                val hasThreeOfAKindWithJokers = groups.any { it.size + jokers >= 3 }

                val hasPairExcludingThreeOfAKind = groups.any { it.size == 2 } ||
                        groups.filter { it.size + jokers < 3 }.any { it.size + jokers >= 2 }

                return hasThreeOfAKindWithJokers && hasPairExcludingThreeOfAKind
            }

            return false
        }

        fun isThreeOfAKind(): Boolean {
            val jokers = jokersCount()
            val groups = cards.groupBy { it.value }.values

            return groups.any { group -> group.size + jokers >= 3 }
        }

        fun isTwoPairs(): Boolean {
            var jokers = jokersCount()
            val groups = cards.groupBy { it.value }.values

            if (groups.filter { it.size >= 2 }.size >= 2) {
                return true
            }

            var pairsFormed = 0

            for (group in groups) {
                val groupSize = group.size

                if (groupSize + jokers >= 2) {
                    pairsFormed++
                    jokers -= (2 - groupSize).coerceAtLeast(0)

                    if (pairsFormed == 2) {
                        return true
                    }
                }
            }

            return false
        }

        fun isOnePair(): Boolean {
            val jokers = jokersCount()
            val groups = cards.groupBy { it.value }.values

            return groups.any { group -> group.size + jokers >= 2 }
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