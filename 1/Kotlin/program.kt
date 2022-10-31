import java.io.File
import java.util.Arrays

fun readFile(filePath: String): List<Int> 
    = File(filePath).readLines()
                    .map() { it.toInt() }

fun countMeasurementIncreases(input: List<Int>) : Int
    = input.zipWithNext()
           .filter { (a, b) -> a < b }
           .count()

fun Part1(filePath: String) {
    var input = readFile(filePath);

    var result = countMeasurementIncreases(input);

    println("Part1 results: " + result)
}

fun Part2(filePath: String) {
    var input = readFile(filePath);

    // calculate three-measurement sliding window
    var slidingWindow = listOf(0).plus(0).plus(input)
        .zip(listOf(0).plus(input)).zip(input)
        .drop(2)
        .map { it.first.first + it.first.second + it.second }

    var result = countMeasurementIncreases(slidingWindow)

    println("Part2 result: " + result)
}

fun main(args: Array<String>) {
    Part1(Arrays.toString(args))
    Part2(Arrays.toString(args))
}