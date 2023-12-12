package day12

import solution.Dataset
import solution.Solution
import java.io.File

class Part1 : Solution {
    override val datasets = setOf(
        Dataset("src/day12/data/test_input_part1.txt", "21"),
        Dataset("src/day12/data/input.txt", "7350")
    )

    override fun getTitle(): String {
        return "Day 12: Hot Springs - Part 1"
    }

    private var possibleCount = 0

    override fun getResult(dataset: Dataset): String {
        var sum = 0
        for (row in File(dataset.filepath).readLines().map { it.split(" ") }) {
            val s = row.first
            val check = row.last.split(",").map { it.toInt() }
            this.possibleCount = 0
            this.findPossible(s, check)
            sum += this.possibleCount
        }
        return sum.toString()
    }

    private fun findPossible(s: String, check: List<Int>) {
        val unknown = s.indexOf("?")
        if (unknown < 0 && isPossible(s, check)) {
            this.possibleCount++
        } else if (unknown > -1) {
            val new = StringBuilder(s)
            "#.".map { new.setCharAt(unknown, it); findPossible(new.toString(), check) }
        }
    }

    private fun isPossible(s: String, check: List<Int>): Boolean {
        val groups = mutableListOf<Int>()
        var count = 0
        for (c in s) {
            if (c.toString() == "#") {
                count++
                continue
            }
            if (count > 0) {
                groups.add(count)
                count = 0
            }
        }
        if (count > 0) {
            groups.add(count)
        }
        return groups == check
    }
}