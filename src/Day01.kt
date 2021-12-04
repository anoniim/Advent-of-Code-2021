fun main() {
    fun part1(input: List<Int>): Int {
        var increaseCount = 0
        input.windowed(2)
            .forEach {
                if (it[0] < it[1]) increaseCount++
            }
        return increaseCount
    }

    fun part2(input: List<Int>): Int {
        var increaseCount = 0
        input.windowed(3, partialWindows = false) { it.sum() }
            .windowed(2)
            .forEach {
                if (it[0] < it[1]) increaseCount++
            }
        return increaseCount
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInts("Day01_test")
    check(part2(testInput) == 5)

    val input = readInts("Day01")
    println(part1(input))
    println(part2(input))
}
