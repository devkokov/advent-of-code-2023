package day4

import solution.Dataset
import solution.Solution
import java.io.File

class Part2: Solution {
    override val datasets = setOf(
        Dataset("src/day4/data/test_input_part1.txt", "30"),
        Dataset("src/day4/data/input.txt")
    )

    override fun getTitle(): String {
        return "Day 4: Scratchcards - Part 2"
    }

    override fun getResult(dataset: Dataset): String {
        var sum = 0
        val copies = mutableMapOf<Int, Int>()
        for ((id, card) in File(dataset.filepath).readLines().withIndex()) {
            val nums = card.substringAfter(": ").split(" | ")
            val getSet = { s: String -> s.split(" ").map { it.trim() }.filter { it != "" }.toSet() }
            val winning = getSet(nums.first) intersect getSet(nums.last)
            val cards = (copies[id] ?: 0) + 1
            winning.indices.map { copies[id + it + 1] = (copies[id + it + 1] ?: 0) + cards }
            sum += cards
        }
        return sum.toString()
    }
}