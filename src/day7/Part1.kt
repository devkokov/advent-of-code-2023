package day7

import solution.Dataset
import solution.Solution
import java.io.File
import kotlin.math.abs

open class Part1: Solution {
    override val datasets = setOf(
        Dataset("src/day7/data/test_input_part1.txt", "6440"),
        Dataset("src/day7/data/input.txt", "248105065")
    )

    open val cards = setOf("A", "K", "Q", "J", "T", "9", "8", "7", "6", "5", "4", "3", "2").map { it.toCharArray().first() }

    override fun getTitle(): String {
        return "Day 7: Camel Cards - Part 1"
    }

    override fun getResult(dataset: Dataset): String {
        return File(dataset.filepath).readLines().map { it.split(" ") }
            .sortedWith { a, b -> compare(a.first, b.first) }
            .foldIndexed(0) { i, acc, v -> acc + (i + 1) * v.last.toInt() }.toString()
    }

    protected open fun getHandScore(hand: String): Int {
        val groups = mutableMapOf<Char, Int>()
        hand.forEach { groups[it] = (groups[it] ?: 0) + 1 }
        return when (groups.maxOf { it.value }) {
            5 -> 7
            4 -> 6
            3 -> if (groups.count() == 2) 5 else 4
            2 -> if (groups.count() == 3) 3 else 2
            else -> 1
        }
    }

    protected open fun compare(a: String, b: String): Int {
        val diff = getHandScore(a) - getHandScore(b)
        if (diff != 0) {
            return diff/abs(diff)
        }
        for (i in 0..4) {
            val cardDiff = this.cards.indexOf(b[i]) - this.cards.indexOf(a[i])
            if (cardDiff != 0) {
                return cardDiff/abs(cardDiff)
            }
        }
        return 0
    }
}