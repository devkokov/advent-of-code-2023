package day7

import solution.Dataset

open class Part2: Part1() {
    override val datasets = setOf(
        Dataset("src/day7/data/test_input_part1.txt", "5905"),
        Dataset("src/day7/data/input.txt", "249515436")
    )

    override val cards = setOf("A", "K", "Q", "T", "9", "8", "7", "6", "5", "4", "3", "2", "J").map { it.toCharArray().first() }

    override fun getTitle(): String {
        return "Day 7: Camel Cards - Part 2"
    }

    override fun getHandScore(hand: String): Int {
        val groups = mutableMapOf<Char, Int>()
        var jokers = 0
        hand.forEach { if (it.toString() == "J") jokers++ else groups[it] = (groups[it] ?: 0) + 1 }
        return when ((groups.maxOfOrNull { it.value } ?: 0) + jokers) {
            5 -> 7
            4 -> 6
            3 -> if (groups.count() == 2) 5 else 4
            2 -> if (groups.count() == 3) 3 else 2
            else -> 1
        }
    }
}