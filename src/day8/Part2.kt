package day8

import solution.Dataset
import solution.Solution
import java.io.File

class Part2 : Solution {
    override val datasets = setOf(
        Dataset("src/day8/data/test_input_part2.txt", "6"),
        Dataset("src/day8/data/input.txt", "15690466351717")
    )

    override fun getTitle(): String {
        return "Day 8: Haunted Wasteland - Part 2"
    }

    override fun getResult(dataset: Dataset): String {
        val file = File(dataset.filepath).readLines()
        val instructions = file.first
        val map = file.drop(2).associate { s ->
            s.substring(0, 3) to s.substringAfter("= ").split(",").map { s2 -> s2.filter { it.isLetterOrDigit() } }
        }

        val startingNodes = map.keys.filter { it.endsWith("A") }
        val zAtSteps = mutableSetOf<Long>()

        for (node in startingNodes) {
            var step = 0
            var current = node

            while (!current.endsWith("Z")) {
                val instructionIndex = step.mod(instructions.length)
                step++
                current = when (instructions[instructionIndex].toString()) {
                    "L" -> map[current]!!.first
                    "R" -> map[current]!!.last
                    else -> throw Exception("Unknown instruction")
                }
            }

            zAtSteps.add(step.toLong())
        }

        return zAtSteps.reduce { acc, v -> findLCM(acc, v) }.toString()
    }

    private fun findLCM(a: Long, b: Long): Long {
        val larger = if (a > b) a else b
        val maxLcm = a * b
        var lcm = larger
        while (lcm <= maxLcm) {
            if (lcm % a == 0.toLong() && lcm % b == 0.toLong()) {
                return lcm
            }
            lcm += larger
        }
        return maxLcm
    }
}