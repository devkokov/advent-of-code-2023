package day1

import solution.Dataset
import solution.Solution
import java.io.File

class Part1: Solution {
    override val datasets = setOf(
        Dataset("src/day1/data/test_input_part1.txt", "142"),
        Dataset("src/day1/data/input.txt", "55834")
    )

    override fun getTitle(): String {
        return "Day 1: Trebuchet?! - Part 1"
    }

    override fun getResult(dataset: Dataset): String {
        var sum = 0
        for (line in File(dataset.filepath).readLines()) {
            val digits = line.filter { it.isDigit() }
            val num = digits.first().toString() + digits.last().toString()
            sum += num.toInt()
        }
        return sum.toString()
    }
}