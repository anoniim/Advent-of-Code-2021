package day09

import readInput

fun main() = Day09().main()

private class Day09 {

    private val className = this::class.simpleName

    fun main() {
        // test if implementation meets criteria from the description:
        val testInput = parseInput("$className/${className}_test")
        check(part1(testInput) == 15)
        check(part2(testInput) == 0)

        val input = parseInput("$className/$className")
        println(part1(input))
        println(part2(input))
    }

    fun part1(input: List<List<Int>>): Int {
        // Sum of the risk levels of all low points
        val gridWidth = input.first().size
        val data = input.flatten()
        return data.filterIndexed { index, current ->
            getNeighboursOf(index, gridWidth, data).none { it < current }
        }.sumOf { it + 1 }
    }

    fun part2(input: List<List<Int>>): Int {
        //
        return 0
    }

    fun getNeighboursOf(i: Int, width: Int, data: List<Int>): List<Int> {
        val neighbors = mutableListOf<Int>()
        val y = i / width
        val x = i % width
        val westIndex = i - 1
        if (westIndex >= 0 && westIndex / width == y) neighbors.add(data[westIndex])
        val northIndex = (y - 1) * width + x
        if (northIndex >= 0) neighbors.add(data[northIndex])
        val eastIndex = i + 1
        if (eastIndex < data.size && eastIndex / width == y) neighbors.add(data[eastIndex])
        val southIndex = (y + 1) * width + x
        if (southIndex < data.size) neighbors.add(data[southIndex])
        return neighbors
    }

    private fun parseInput(inputFile: String): List<List<Int>> {
        return readInput(inputFile).map { line -> line.map { it.digitToInt() } }
    }
}