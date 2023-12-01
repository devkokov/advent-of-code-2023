package solution

interface Solution {
    val datasets: Set<Dataset>
    fun getTitle(): String
    fun getResult(dataset: Dataset): String
}