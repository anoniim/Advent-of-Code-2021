fun main() {
    Day06().main()
}

private class Day06 {

    fun main() {
        // test if implementation meets criteria from the description:
        val testInput = parseInput("${this::class.simpleName}_test")
        check(part1(testInput) == 5934)
        check(part2(testInput) == 0)

        val input = parseInput("${this::class.simpleName}")
        println(part1(input))
        println(part2(input))
    }

    fun part1(input: List<Int>): Int {
        // How many lanternfish are there after 80 days?
        var fish = input
        repeat(80) {
            val newFish = mutableListOf<Int>()
            fish.forEach {
                if (it == 0) {
                    newFish.add(8)
                    newFish.add(6)
                } else newFish.add(it - 1)
            }
            fish = newFish
        }
        return fish.size
    }

    fun part2(input: List<Int>): Int {
        return 0
    }

    private fun parseInput(inputFile: String) = readInput(inputFile)[0].split(',').map { it.toInt() }
}