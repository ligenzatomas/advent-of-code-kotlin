package twenty

class Day07 {
    constructor() {
        secondPart()
    }

    fun secondPart() {
        println(getCounts("shiny gold"))
    }

    fun getCounts(nameInput: String): Int {
        val inputs = Utils().parseLines("07")

        var count = 0

        inputs.forEach {
            val results = "^([a-z\\s]*) bags contain (.*).$".toRegex().find(it)!!
            val name = results.groupValues.get(1)

            if (name == nameInput) {
                val map = mutableMapOf<String, Int>()

                results.groupValues.get(2).split(", ").forEach { under ->
                    if (!under.equals("no other bags")) {
                        val res = "^([\\d]*) ([a-z\\s]*) bag|bags$".toRegex().find(under)!!
                        map.put(res.groupValues.get(2), res.groupValues.get(1).toInt())
                    }
                }

                map.forEach { t, u ->
                    count += u
                    count += u * getCounts(t)
                }
            }
        }

        return count
    }

    fun firstPart() {
        val result = mutableSetOf<String>()
        result.addAll(getName("shiny gold"))
        println(result.count())
    }

    fun getName(nameInput: String): MutableSet<String> {
        val inputs = Utils().parseLines("07")

        var set = mutableSetOf<String>()

        inputs.forEach {
            val results = "^([a-z\\s]*) bags contain (.*).$".toRegex().find(it)!!
            val name = results.groupValues.get(1)
            val map = mutableMapOf<String, Int>()

            results.groupValues.get(2).split(", ").forEach { under ->
                if (!under.equals("no other bags")) {
                    val res = "^([\\d]*) ([a-z\\s]*) bag|bags$".toRegex().find(under)!!
                    //println(under)
                    map.put(res.groupValues.get(2), res.groupValues.get(1).toInt())
                }
            }

            if (map.contains(nameInput)) {
                set.add(name)
                println(it)
            }
        }

        if (set.isNotEmpty()) {
            set.addAll(set.map(this::getName).flatten())
        }

        return set
    }
}