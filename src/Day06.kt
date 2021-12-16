fun main() {
    Day06().main()
}

private class Day06 {

    fun main() {
        // test if implementation meets criteria from the description:
        val testInput = parseInput("${this::class.simpleName}_test")
        check(part1(testInput) == 5934)
        check(part2(testInput) == 26984457539)

        val input = parseInput("${this::class.simpleName}")
        println(part1(input))
//        println(part2(input))
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

    fun part2(input: List<Int>): Long {
        // How many lanternfish are there after 256 days?
        return input.sumOf { sumAllInGeneration(0, it+1, 256) }
    }

    fun sumAllInGeneration(day: Int, timeToNextGen: Int, maxDay: Int): Long {
        val nexGenDay = day + timeToNextGen
        if (nexGenDay > maxDay) return 1
        var sumOfAllChildren = 0L
        for(birthDay in nexGenDay .. maxDay step 7) {
            sumOfAllChildren += sumAllInGeneration(birthDay, 9, maxDay)
        }
        return 1L + sumOfAllChildren
    }

    private fun parseInput(inputFile: String) = readInput(inputFile)[0].split(',').map { it.toInt() }
}