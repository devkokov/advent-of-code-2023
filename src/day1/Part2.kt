package day1

import solution.Dataset
import solution.Solution
import java.io.File

class Part2: Solution {
    override val datasets = setOf(
        Dataset("src/day1/data/test_input_part2.txt", "281"),
        Dataset("src/day1/data/input.txt", "53221")
    )

    override fun getTitle(): String {
        return "Day 1: Trebuchet?! - Part 2"
    }

    override fun getResult(dataset: Dataset): String {
        val digits = setOf(
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

        var sum = 0
        for (line in File(dataset.filepath).readLines()) {
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
        }
        return sum.toString()
    }
}