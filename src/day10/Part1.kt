package day10

import solution.Dataset
import solution.Solution
import java.io.File

open class Part1 : Solution {
    override val datasets = setOf(
        Dataset("src/day10/data/test_input_part1.txt", "8"),
        Dataset("src/day10/data/input.txt", "6927")
    )

    private val opposites = mapOf(
        "up" to "down",
        "down" to "up",
        "left" to "right",
        "right" to "left"
    )

    private val dirToPipe = mapOf(
        "up" to setOf("|", "7", "F"),
        "down" to setOf("|", "L", "J"),
        "right" to setOf("-", "J", "7"),
        "left" to setOf("-", "L", "F")
    )

    private val pipeToDir = mapOf(
        "|" to setOf("up", "down"),
        "-" to setOf("left", "right"),
        "L" to setOf("up", "right"),
        "J" to setOf("up", "left"),
        "7" to setOf("down", "left"),
        "F" to setOf("down", "right"),
    )

    protected val grid = mutableListOf<MutableList<String>>()

    protected val pipeCoords = mutableMapOf<String, Pair<Pair<Int, Int>, String>>()

    private var stepCount = 0

    protected var loopDirection = 0

    override fun getTitle(): String {
        return "Day 10: Pipe Maze - Part 1"
    }

    override fun getResult(dataset: Dataset): String {
        this.processGrid(dataset)
        return (this.stepCount / 2).toString()
    }

    protected fun processGrid(dataset: Dataset) {
        this.grid.clear()
        var sCoords = Pair(-1, -1)

        for ((i, line) in File(dataset.filepath).readLines().withIndex()) {
            val chars = mutableListOf<String>()
            for ((j, char) in line.withIndex()) {
                chars.add(char.toString())
                if (char.toString() == "S") {
                    sCoords = Pair(i, j)
                }
            }
            this.grid.add(chars)
        }

        possibleStart@for ((pipe, dirs) in this.pipeToDir) {
            if (dirs.count { canTravel(sCoords, it) } != 2) {
                continue
            }
            this.grid[sCoords.first][sCoords.second] = pipe
            this.pipeCoords.clear()
            this.stepCount = 0
            this.loopDirection = 0

            var current = sCoords
            var direction = dirs.first()

            do {
                if (!canTravel(current, direction)) {
                    continue@possibleStart
                }
                current = getNext(current, direction)
                this.pipeCoords["${current.first}:${current.second}"] = Pair(current, direction)
                this.stepCount++
                this.loopDirection += when(char(current)) {
                    "L" -> if (direction == "down") -1 else 1
                    "J" -> if (direction == "down") 1 else -1
                    "F" -> if (direction == "up") 1 else -1
                    "7" -> if (direction == "up") -1 else 1
                    else -> 0
                }
                direction = this.pipeToDir[char(current)]!!.filter { it != this.opposites[direction] }.first
            } while (current != sCoords)

            return
        }

        throw PipeException("No loop found")
    }

    private fun canTravel(coords: Pair<Int, Int>, direction: String): Boolean {
        return try {
            this.dirToPipe[direction]!!.contains(char(getNext(coords, direction)))
        } catch (e: PipeException) {
            false
        }
    }

    protected fun char(coords: Pair<Int, Int>): String {
        return this.grid[coords.first][coords.second]
    }

    private fun getNext(coords: Pair<Int, Int>, direction: String): Pair<Int, Int> {
        val next = when (direction) {
            "down" -> Pair(coords.first + 1, coords.second)
            "up" -> Pair(coords.first - 1, coords.second)
            "left" -> Pair(coords.first, coords.second - 1)
            "right" -> Pair(coords.first, coords.second + 1)
            else -> throw PipeException("invalid direction")
        }

        if (next.first < 0 || next.first >= this.grid.count() || next.second < 0 || next.second >= this.grid.first.count()) {
            throw PipeException("out of bounds")
        }

        return next
    }
}