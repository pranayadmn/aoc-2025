fun main() {
    fun part1(input: List<Long>): Long {
        val invalidIds = mutableListOf<Long>()

        for (id in input) {
            val idString = id.toString()
            if (idString.length % 2 == 0) {
                val half = idString.length / 2
                val firstHalf = idString.take(half)
                val secondHalf = idString.takeLast(half)
                if (firstHalf == secondHalf) invalidIds.add(id)
            }
        }
        return invalidIds.sum()
    }

    fun part2(input: List<Long>): Long {
        val invalidIds = mutableListOf<Long>()

        for (id in input) {
            val idString = id.toString()
            val maxChunkSize = idString.length / 2
            for (chunkSize in 1..maxChunkSize) {
                val chunks = idString.chunked(chunkSize)
                val invalid = chunks.all { it == chunks.first() }
                if (invalid) {
                    invalidIds.add(id)
                    break
                }
            }
        }
        return invalidIds.sum()
    }

    fun parseInput(input: String): List<Long> = input.split(",").flatMap {
        val ends = it.split("-")
        LongRange(ends.first().toLong(), ends.last().toLong())
    }

    val testInput = parseInput(readInput("Day02_test").joinToString(","))
    check(part1(testInput) == 1227775554L)
    check(part2(testInput) == 4174379265L)

    val input = parseInput(readInput("Day02").joinToString(","))
    part1(input).println()
    part2(input).println()
}
