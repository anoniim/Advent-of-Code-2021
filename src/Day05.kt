fun main() {
    Day05().main()
}

private class Day05 {

    fun main() {
        fun part1(input: List<Pair<Point, Point>>): Int {
            // Find the most dangerous areas
            val map = mutableMapOf<Point, Int>()
            input.filter(this::onlyHorizontalAndVertical)
                .map(this::toListOfPoints)
                .flatten()
                .forEach { point ->
                    val count = map[point] ?: 0
                    map[point] = count + 1
                }
            return map.count { it.value >= 2 }
        }

        fun part2(input: List<Pair<Point, Point>>): Int {
            return 0
        }

        // test if implementation meets criteria from the description, like:
        val testInput = parseInput("${this::class.simpleName}_test")
        check(part1(testInput) == 5)
        check(part2(testInput) == 0)

        val input = parseInput("${this::class.simpleName}")
        println(part1(input))
        println(part2(input))
    }

    private fun onlyHorizontalAndVertical(pair: Pair<Point, Point>): Boolean {
        return pair.first.x == pair.second.x || pair.first.y == pair.second.y
    }

    private fun toListOfPoints(pair: Pair<Point, Point>): List<Point> {
        val xRange = IntProgression.fromClosedRange(pair.first.x, pair.second.x, getStep(pair.first.x, pair.second.x))
        val yRange = IntProgression.fromClosedRange(pair.first.y, pair.second.y, getStep(pair.first.y, pair.second.y))
        return xRange.flatMap { x -> yRange.map { y -> Point(x, y) } }
    }

    private fun getStep(first: Int, second: Int): Int {
        return if (first <= second) 1 else -1
    }

    private data class Point(val x: Int, val y: Int) {
        companion object {
            fun parse(xy: String): Point {
                val (x, y) = xy.split(',')
                return Point(x.toInt(), y.toInt())
            }
        }
    }

    private fun parseInput(inputFile: String) = readInput(inputFile)
        .map { it.split(" -> ")
            .map { pointString -> Point.parse(pointString) }
        }.map { Pair(it[0], it[1]) }
}