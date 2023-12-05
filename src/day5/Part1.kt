package day5

import solution.Dataset
import solution.Solution
import java.io.File
import java.math.BigInteger

class Part1: Solution {
    override val datasets = setOf(
        Dataset("src/day5/data/test_input_part1.txt", "35"),
        Dataset("src/day5/data/input.txt", "214922730")
    )

    override fun getTitle(): String {
        return "Day 5: If You Give A Seed A Fertilizer - Part 1"
    }

    override fun getResult(dataset: Dataset): String {
        var seeds = mapOf<BigInteger, BigInteger>()
        var seedMap = mutableListOf<List<BigInteger>>()

        for (line in File(dataset.filepath).readLines()) {
            if (line.trim() == "") {
                continue
            }
            if (line.startsWith("seeds: ")) {
                seeds = line.substringAfter("seeds: ").split(" ").associate { it.toBigInteger() to it.toBigInteger() }
                continue
            }
            if (line.contains("-to-")) {
                if (seedMap.isNotEmpty()) {
                    seeds = seeds.mapValues { getDestination(it.value, seedMap) }
                }
                seedMap = mutableListOf()
                continue
            }
            seedMap.add(line.split(" ").map { it.toBigInteger() })
        }
        seeds = seeds.mapValues { getDestination(it.value, seedMap) }

        return seeds.values.min().toString()
    }

    private fun getDestination(source: BigInteger, seedMap: MutableList<List<BigInteger>>): BigInteger {
        for (mapping in seedMap) {
            if (source >= mapping[1] && source <= mapping[1] + mapping[2]) {
                return source + mapping[0] - mapping[1]
            }
        }
        return source
    }
}