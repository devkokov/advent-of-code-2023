package day5

import solution.Dataset
import solution.Solution
import java.io.File
import java.math.BigInteger

class Part2: Solution {
    override val datasets = setOf(
        Dataset("src/day5/data/test_input_part1.txt", "46"),
        Dataset("src/day5/data/input.txt", "148041808")
    )

    override fun getTitle(): String {
        return "Day 5: If You Give A Seed A Fertilizer - Part 2"
    }

    override fun getResult(dataset: Dataset): String {
        var sources = listOf<Pair<BigInteger, BigInteger>>()
        var destMap = mutableListOf<List<BigInteger>>()

        for (line in File(dataset.filepath).readLines()) {
            if (line.trim() == "") {
                continue
            }
            if (line.startsWith("seeds: ")) {
                val seedsList = line.substringAfter("seeds: ").split(" ").map { it.toBigInteger() }
                for (i in 0..<seedsList.count() step 2) {
                    sources = sources + listOf(Pair(seedsList[i], seedsList[i] + seedsList[i+1] - BigInteger.ONE))
                }
                continue
            }
            if (line.contains("-to-")) {
                if (destMap.isNotEmpty()) {
                    sources = translateSources(sources, destMap)
                }
                destMap = mutableListOf()
                continue
            }
            destMap.add(line.split(" ").map { it.toBigInteger() })
        }
        sources = translateSources(sources, destMap)

        return sources.minOfOrNull { it.first }.toString()
    }

    private fun translateSources(sources: List<Pair<BigInteger, BigInteger>>, destMap: MutableList<List<BigInteger>>): List<Pair<BigInteger, BigInteger>> {
        val destinations = mutableListOf<Pair<BigInteger, BigInteger>>()
        for (source in sources.sortedBy { it.first }) {
            val translatedSources = mutableListOf<Pair<BigInteger, BigInteger>>()
            for (mapping in destMap) {
                val mappingSource = Pair(mapping[1], mapping[1] + mapping[2] - BigInteger.ONE)
                val diff = mapping[0] - mapping[1]
                if (source.second >= mappingSource.first && source.first <= mappingSource.second) {
                    val newSourceRange = Pair(maxOf(source.first, mappingSource.first), minOf(source.second, mappingSource.second))
                    val destRange = Pair(newSourceRange.first + diff, newSourceRange.second + diff)
                    destinations.add(destRange)
                    translatedSources.add(newSourceRange)
                }
            }
            if (translatedSources.isEmpty()) {
                destinations.add(source)
                continue
            }
            val sorted = translatedSources.sortedBy { it.first }
            if (sorted.first.first > source.first) {
                destinations.add(Pair(source.first, sorted.first.first - BigInteger.ONE))
            }
            if (sorted.last.second < source.second) {
                destinations.add(Pair(sorted.last.second + BigInteger.ONE, source.second))
            }
            val count = sorted.count()
            for ((i, translated) in sorted.withIndex()) {
                if (i + 1 == count) {
                    break
                }
                if (translated.second + BigInteger.ONE != sorted[i + 1].first) {
                    destinations.add(Pair(translated.second + BigInteger.ONE, sorted[i + 1].first - BigInteger.ONE))
                }
            }
        }
        return destinations.sortedBy { it.first }
    }
}