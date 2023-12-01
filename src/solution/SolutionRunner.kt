package solution

class SolutionRunner(private val solutions: Set<Solution>) {
    fun letsGo() {
        while (true) {
            outputListOfSolutions()
            val selection = getUserSelection()
            if (selection == null) {
                println("Invalid selection. Press enter to continue")
                readlnOrNull()
                continue
            }
            runSolution(solutions.elementAt(selection))
        }
    }

    private fun outputListOfSolutions() {
        println("\nSolutions:")
        for ((i, solution) in solutions.withIndex()) {
            println("[${i+1}] ${solution.getTitle()}")
        }
    }

    private fun getUserSelection(): Int? {
        print("\nSelect solution to run: ")

        val selected = readlnOrNull()
        val parsed: Int

        try {
            parsed = (selected ?: "").toInt() - 1
        } catch (nfe: NumberFormatException) {
            return null
        }

        if (!solutions.indices.contains(parsed)) {
            return null
        }

        return parsed
    }

    private fun runSolution(solution: Solution) {
        val title = "Running ${solution.getTitle()}"
        println("-".repeat(title.length))
        println(title)
        println("-".repeat(title.length))

        solution.run()

        println("\nDONE. Press enter to continue")
        readlnOrNull()
    }
}