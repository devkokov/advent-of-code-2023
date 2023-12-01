fun main() {
    val solutions: List<Solution> = listOf(
        day1.Part1(),
        day1.Part2()
    )

    while (true) {
        println("\nSolutions:")
        for ((i, solution) in solutions.withIndex()) {
            println("[${i+1}] ${solution.getTitle()}")
        }

        print("\nSelect solution to run: ")

        val selected = readlnOrNull()
        val parsed: Int

        try {
            parsed = (selected ?: "").toInt() - 1
        } catch (nfe: NumberFormatException) {
            println("Invalid selection. Press enter to continue")
            readlnOrNull()
            continue
        }

        if (!solutions.indices.contains(parsed)) {
            println("Invalid selection. Press enter to continue")
            readlnOrNull()
            continue
        }

        val solution = solutions[parsed]

        val title = "Running ${solution.getTitle()}"
        println("-".repeat(title.length))
        println(title)
        println("-".repeat(title.length))

        solution.run()

        println("\nDONE. Press enter to continue")
        readlnOrNull()
    }
}