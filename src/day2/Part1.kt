package day2

import solution.Dataset
import solution.Solution
import java.io.File

open class Part1: Solution {
    override val datasets = setOf(
        Dataset("src/day2/data/test_input_part1.txt", "8"),
        Dataset("src/day2/data/input.txt", "2406")
    )

    override fun getTitle(): String {
        return "Day 2: Cube Conundrum - Part 1"
    }

    override fun getResult(dataset: Dataset): String {
        var sum = 0
        gameLoop@ for ((index, line) in File(dataset.filepath).readLines().withIndex()) {
            for (round in line.substringAfter(": ").split("; ")) {
                if (!isRoundPossible(round.split(", "))) {
                    continue@gameLoop
                }
            }
            sum += index + 1
        }
        return sum.toString()
    }

    private fun isRoundPossible(round: List<String>): Boolean
    {
        val check: Map<String, Int> = mapOf(
            "red" to 12,
            "green" to 13,
            "blue" to 14
        )
        for (cubes in round) {
            val (num, colour) = cubes.split(" ").map { it.trim() }
            if (num.toInt() > check[colour]!!) {
                return false
            }
        }
        return true
    }
}