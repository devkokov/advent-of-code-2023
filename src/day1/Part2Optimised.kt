package day1

import solution.Dataset
import solution.Solution
import java.io.File

class Part2Optimised: Solution {
    override val datasets = setOf(
        Dataset("src/day1/data/test_input_part2.txt", "281"),
        Dataset("src/day1/data/input.txt", "53221")
    )

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
        return "Day 1: Trebuchet?! - Part 2 (Optimised)"
    }

    override fun getResult(dataset: Dataset): String {
        var sum = 0
        for (line in File(dataset.filepath).readLines()) {
            val first = getFirstDigit(line)
            val last = getFirstDigit(line, true)
            sum += "$first$last".toInt()
        }
        return sum.toString()
    }

    private fun getFirstDigit(line: String, reversed: Boolean = false): String
    {
        val indices = if (reversed) line.indices.reversed() else line.indices
        var buffer = ""
        for (pos in indices) {
            if (line[pos].isDigit()) {
                return line[pos].toString()
            }

            buffer = if (reversed) line[pos] + buffer else buffer + line[pos]

            for ((word, digit) in digits) {
                if (buffer != buffer.replace(word, digit)) {
                    return digit
                }
            }
        }

        throw Exception("No digit found")
    }
}