package day06

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import readInput
import kotlin.system.measureTimeMillis

fun main() {
    Day06().main()
}

private class Day06 {

    fun main() {
        // test if implementation meets criteria from the description:
        val testInput = parseInput("${this::class.simpleName}_test")
        check(part1(testInput) == 5934)
        measureTimeMillis {
            check(part2(testInput) == 26984457539)
        }.also { time -> println("Completed in ${time/1000}s") }

        measureTimeMillis {
            check(part2MultiThreaded(testInput) == 26984457539)
        }.also { time -> println("Completed in ${time/1000}s") }

        val input = parseInput("${this::class.simpleName}")
        println(part1(input))
//        println(part2(input)) // FIXME takes way too long
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
        if (nexGenDay > maxDay) return 1L
        var sumOfAllChildren = 0L
        for(birthDay in nexGenDay .. maxDay step 7) {
            sumOfAllChildren += sumAllInGeneration(birthDay, 9, maxDay)
        }
        return 1L + sumOfAllChildren
    }

    fun part2MultiThreaded(input: List<Int>): Long {
        // How many lanternfish are there after 256 days?
        return runBlocking {
            input.map {
                async { sumAllInGenerationSuspended(0, it+1, 256) }
            }.sumOf { it.await() }
        }
    }

    suspend fun sumAllInGenerationSuspended(day: Int, timeToNextGen: Int, maxDay: Int): Long = withContext(Dispatchers.Default) {
        val nexGenDay = day + timeToNextGen
        if (nexGenDay > maxDay) return@withContext 1L
        var sumOfAllChildren = 0L
        for(birthDay in nexGenDay .. maxDay step 7) {
            sumOfAllChildren += sumAllInGenerationSuspended(birthDay, 9, maxDay)
        }
        1L + sumOfAllChildren
    }

    private fun parseInput(inputFile: String) = readInput(inputFile)[0].split(',').map { it.toInt() }
}