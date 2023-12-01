package day1

import Solution
import java.io.File

class Part2: Solution {
    private val digits = setOf(
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9",
    )

    override fun getTitle(): String {
        return "Day 1: Trebuchet?! - Part 2"
    }

    override fun run() {
        val example = "two1nine\n" +
                "eightwothree\n" +
                "abcone2threexyz\n" +
                "xtwone3four\n" +
                "4nineeightseven2\n" +
                "zoneight234\n" +
                "7pqrstsixteen"

        println("Running against example dataset")

        var result = getResult(example)

        print("Result $result - ")

        if (result == 281) {
            println("OK")
        } else {
            println("FAIL")
            return
        }

        println("Running against large dataset")

        result = getResult(File("src/day1/input.txt").readText())
        println("Result $result")
    }

    private fun getResult(input: String): Int {
        var sum = 0
        for (line in input.split("\n")) {
            val positions: MutableMap<Int, String> = mutableMapOf()
            for ((word, digit) in digits) {
                positions[line.indexOf(word)] = digit
                positions[line.lastIndexOf(word)] = digit
                positions[line.indexOf(digit)] = digit
                positions[line.lastIndexOf(digit)] = digit
            }
            val sortedPositions = positions.filterKeys { it > -1 }.toSortedMap { a, b -> a.compareTo(b) }
            val num = sortedPositions.firstEntry().value + sortedPositions.lastEntry().value
            sum += num.toInt()
            println("${line.padEnd(60, " ".first())} -> $num")
        }
        return sum
    }
}