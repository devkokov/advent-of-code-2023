package day2

import solution.Dataset
import solution.Solution
import java.io.File

open class Part2: Solution {
    override val datasets = setOf(
        Dataset("src/day2/data/test_input_part1.txt", "2286"),
        Dataset("src/day2/data/input.txt", "78375")
    )

    override fun getTitle(): String {
        return "Day 2: Cube Conundrum - Part 2"
    }

    override fun getResult(dataset: Dataset): String {
        var sum = 0
        for (line in File(dataset.filepath).readLines()) {
            val maxOfEach = mutableMapOf(
                "red" to 0,
                "green" to 0,
                "blue" to 0
            )
            for (round in line.substringAfter(": ").split("; ")) {
                for (cubes in round.split(", ")) {
                    val (num, colour) = cubes.split(" ").map { it.trim() }
                    if (num.toInt() > maxOfEach[colour]!!) {
                        maxOfEach[colour] = num.toInt()
                    }
                }
            }
            sum += maxOfEach.values.reduce {power, value -> power * value}
        }
        return sum.toString()
    }
}