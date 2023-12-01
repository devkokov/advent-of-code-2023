package day1

import solution.Dataset
import solution.Solution
import java.io.File

open class Part1: Solution {
    open val datasets = setOf(
        Dataset("src/day1/data/test_input_part1.txt", "142"),
        Dataset("src/day1/data/input.txt", "55834")
    )

    override fun getTitle(): String {
        return "Day 1: Trebuchet?! - Part 1"
    }

    override fun run() {
        for (dataset in datasets) {
            println("Running against dataset: ${dataset.filepath}")

            val result = getResult(File(dataset.filepath).readText())
            print("Result $result")

            if (dataset.answer == null) {
                return
            }

            println(" - " + if (dataset.answer == result.toString()) "OK" else "FAIL")
        }
    }

    open fun getResult(input: String): Int {
        var sum = 0
        for (line in input.split("\n")) {
            val digits = line.filter { it.isDigit() }
            val num = digits.first().toString() + digits.last().toString()
            sum += num.toInt()
        }
        return sum
    }
}