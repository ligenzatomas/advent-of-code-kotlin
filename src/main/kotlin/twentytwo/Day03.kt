package twentytwo

class Day03 {
    init {
        firstPart()
        secondPart()
    }

    private fun firstPart() {
        val inputs = Utils().parseLines("03")

        println( inputs
            .map { Rucksack(Pair( it.substring(0, (it.length/2)), it.substring(it.length/2, it.length)))}
            .map { it.findSameCharacter() }
            .sumOf { valuateChar(it) })
    }

    private fun secondPart() {
        val inputs = Utils().parseLines("03")
        val groups: MutableList<GroupWithBadge> = mutableListOf()

        for (i in 0..inputs.size-3 step 3) {
            groups.add(GroupWithBadge(inputs.subList(i, i+3)))
        }

        println(
            groups.map { it.findBadge() }.sumOf { valuateChar(it) }
        )
    }

    private fun valuateChar(char: Char): Int {
        return when (char) {
            'a' -> 1
            'b' -> 2
            'c' -> 3
            'd' -> 4
            'e' -> 5
            'f' -> 6
            'g' -> 7
            'h' -> 8
            'i' -> 9
            'j' -> 10
            'k' -> 11
            'l' -> 12
            'm' -> 13
            'n' -> 14
            'o' -> 15
            'p' -> 16
            'q' -> 17
            'r' -> 18
            's' -> 19
            't' -> 20
            'u' -> 21
            'v' -> 22
            'w' -> 23
            'x' -> 24
            'y' -> 25
            'z' -> 26
            'A' -> 27
            'B' -> 28
            'C' -> 29
            'D' -> 30
            'E' -> 31
            'F' -> 32
            'G' -> 33
            'H' -> 34
            'I' -> 35
            'J' -> 36
            'K' -> 37
            'L' -> 38
            'M' -> 39
            'N' -> 40
            'O' -> 41
            'P' -> 42
            'Q' -> 43
            'R' -> 44
            'S' -> 45
            'T' -> 46
            'U' -> 47
            'V' -> 48
            'W' -> 49
            'X' -> 50
            'Y' -> 51
            'Z' -> 52

            else -> throw RuntimeException("chyba")
        }
    }

    class GroupWithBadge(private val rucksacks: List<String>) {
        fun findBadge(): Char {
            val set1: MutableSet<Char> = HashSet()
            val set2: MutableSet<Char> = HashSet()
            val set3: MutableSet<Char> = HashSet()

            for (c in rucksacks.get(0).toCharArray()) {
                set1.add(c)
            }
            for (c in rucksacks.get(1).toCharArray()) {
                set2.add(c)
            }
            for (c in rucksacks.get(2).toCharArray()) {
                set3.add(c)
            }

            set1.retainAll(set2)
            set1.retainAll(set3)

            if (set1.size == 0) {
                throw RuntimeException("")
            }

            return set1.first()
        }
    }

    class Rucksack(private val pair: Pair<String, String>) {
        fun findSameCharacter(): Char {
            val set1: MutableSet<Char> = HashSet()
            val set2: MutableSet<Char> = HashSet()

            for (c in pair.first.toCharArray()) {
                set1.add(c)
            }
            for (c in pair.second.toCharArray()) {
                set2.add(c)
            }

            set1.retainAll(set2)

            if (set1.size == 0) {
                throw RuntimeException("")
            }

            return set1.first()
        }
    }
}