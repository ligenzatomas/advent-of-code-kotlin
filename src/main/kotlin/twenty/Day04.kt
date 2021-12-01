package twenty

import java.io.File

class Day04 {
    constructor() {
        secondPart()
    }

    fun secondPart() {
        val inputs = readFileAsLinesUsingUseLines("/home/tomasligenza/Dropbox/docMy/workspace/AdventOfCode/src/main/resources/day04.txt")
        var valid = 0;
        var passport = Passport()

        for (item in inputs) {
            println(item)
            println(item.isEmpty())

            if (item.isEmpty()) {
                if (passport.isValid()) valid++
                println(passport)
                passport = Passport()
                continue;
            }

            item.split(' ').forEach {
                println(it)
                if ("^byr:([\\d]{4})$".toRegex().matches(it)) {
                    passport.byr = "^byr:([\\d]{4})$".toRegex().find(it)!!.groupValues[1].toIntOrNull()
                    //println("byr " + passport.byr)
                }

                if ("^iyr:([\\d]{4})$".toRegex().matches(it)) {
                    passport.iyr = "^iyr:([\\d]{4})$".toRegex().find(it)!!.groupValues[1].toIntOrNull()
                }

                if ("^eyr:([\\d]{4})$".toRegex().matches(it)) {
                    passport.eyr = "^eyr:([\\d]{4})$".toRegex().find(it)!!.groupValues[1].toIntOrNull()
                }

                if ("^hgt:([\\d]*)(cm|in)$".toRegex().matches(it)) {
                    val matchResult = "^hgt:([\\d]*)(cm|in)$".toRegex().find(it)!!

                    if (matchResult.groupValues[2] == "cm" && matchResult.groupValues[1].toIntOrNull()!! >= 150 && matchResult.groupValues[1].toIntOrNull()!! <= 193) {
                        passport.hgt = matchResult.groupValues[1].toIntOrNull()
                    }

                    if (matchResult.groupValues[2] == "in" && matchResult.groupValues[1].toIntOrNull()!! >= 59 && matchResult.groupValues[1].toIntOrNull()!! <= 76) {
                        passport.hgt = matchResult.groupValues[1].toIntOrNull()
                    }
                }

                if ("^hcl:(#[0-9a-f]{6})$".toRegex().matches(it)) {
                    passport.hcl = "^hcl:(#[0-9a-f]{6})$".toRegex().find(it)!!.groupValues[1]
                }

                if ("^ecl:(amb|blu|brn|gry|grn|hzl|oth)$".toRegex().matches(it)) {
                    passport.ecl = "^ecl:(amb|blu|brn|gry|grn|hzl|oth)$".toRegex().find(it)!!.groupValues[1]
                }

                if ("^pid:([\\d]{9})$".toRegex().matches(it)) {
                    passport.pid = "^pid:([\\d]{9})$".toRegex().find(it)!!.groupValues[1]
                }

                if ("^cid:(.*)$".toRegex().matches(it)) {
                    passport.cid = "^cid:(.*)$".toRegex().find(it)!!.groupValues[1]
                }
            }
        }

        if (passport.isValid()) valid++
        println(passport)

        println(valid)
    }

    fun firstPart() {
        val inputs = readFileAsLinesUsingUseLines("/home/tomasligenza/Dropbox/docMy/workspace/AdventOfCode/src/main/resources/day04.txt")
        var valid = 0;
        var passport = Passport()

        for (item in inputs) {
            println(item)
            println(item.isEmpty())

            if (item.isEmpty()) {
                if (passport.isValid()) valid++
                println(passport)
                passport = Passport()
                continue;
            }

            item.split(' ').forEach {
                println(it)
                if ("^byr:(.*)$".toRegex().matches(it)) {
                    //passport.byr = "^byr:(.*)$".toRegex().find(it)!!.groupValues[1]
                    //println("byr " + passport.byr)
                }

                if ("^iyr:(.*)$".toRegex().matches(it)) {
                    //passport.iyr = "^iyr:(.*)$".toRegex().find(it)!!.groupValues[1]
                }

                if ("^eyr:(.*)$".toRegex().matches(it)) {
                    //passport.eyr = "^eyr:(.*)$".toRegex().find(it)!!.groupValues[1]
                }

                if ("^hgt:(.*)$".toRegex().matches(it)) {
                    //passport.hgt = "^hgt:(.*)$".toRegex().find(it)!!.groupValues[1]
                }

                if ("^hcl:(.*)$".toRegex().matches(it)) {
                    passport.hcl = "^hcl:(.*)$".toRegex().find(it)!!.groupValues[1]
                }

                if ("^ecl:(.*)$".toRegex().matches(it)) {
                    passport.ecl = "^ecl:(.*)$".toRegex().find(it)!!.groupValues[1]
                }

                if ("^pid:(.*)$".toRegex().matches(it)) {
                    passport.pid = "^pid:(.*)$".toRegex().find(it)!!.groupValues[1]
                }

                if ("^cid:(.*)$".toRegex().matches(it)) {
                    passport.cid = "^cid:(.*)$".toRegex().find(it)!!.groupValues[1]
                }
            }
        }

        if (passport.isValid()) valid++
        println(passport)

        println(valid)
    }

    private fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }

    class Passport {
        constructor() {}

        var byr: Int? = null //(Birth Year)
        var iyr: Int? = null //(Issue Year)
        var eyr: Int? = null //(Expiration Year)
        var hgt: Int? = null //(Height)
        var hcl: String? = null //(Hair Color)
        var ecl: String? = null //(Eye Color)
        var pid: String? = null //(Passport ID)
        var cid: String? = null //(Country ID)

        fun isValid(): Boolean {
            return byr != null && byr!! >= 1920 && byr!! <= 2002
                    && iyr != null && iyr!! >= 2010 && iyr!! <= 2020
                    && eyr != null && eyr!! >= 2020 && eyr!! <= 2030
                    && hgt != null
                    && hcl != null && ecl != null && pid != null
        }

        override fun toString(): String {
            return "Passport(byr=$byr, iyr=$iyr, eyr=$eyr, hgt=$hgt, hcl=$hcl, ecl=$ecl, pid=$pid, cid=$cid)"
        }
    }
}