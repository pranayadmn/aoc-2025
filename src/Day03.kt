data class DigitAtIdx(val digit: Char, var index: Int)

fun maxJoltageTwo(bank: String): Int {
    for (i in 99 downTo 11) {
        val tensDigit = (i / 10).digitToChar()
        val onesDigit = (i % 10).digitToChar()

        val tensIdx = bank.indexOf(tensDigit)
        if (tensIdx == -1) continue

        val onesIdx = bank.indexOf(onesDigit, startIndex = tensIdx + 1)
        if (onesIdx == -1) continue

        return i
    }
    error("Shouldn't happen")
}

fun part1(input: List<String>): Int = input.sumOf { maxJoltageTwo(it) }

fun largestDigit(bank: String, startIdx: Int, digitsLeft: Int): DigitAtIdx {
    if (startIdx > bank.lastIndex) error("The math isn't mathing")
    for (largestDigit in '9' downTo '1') {
        val digitIdx = bank.indexOf(largestDigit, startIdx)
        if (digitIdx == -1) continue
        if (bank.length - digitIdx > digitsLeft) {
            return DigitAtIdx(largestDigit, digitIdx)
        }
    }
    error("Shouldn't happen")
}

fun maxJoltageTwelve(bank: String): Long {
    var numString = ""
    var startIdx = 0
    for (i in 12 downTo 1) {
        val digitsLeft = i - 1
        val digitAtIdx = largestDigit(bank, startIdx, digitsLeft)
        numString += digitAtIdx.digit
        startIdx = digitAtIdx.index + 1
    }
    return numString.toLong()
}

fun part2(input: List<String>): Long = input.sumOf { maxJoltageTwelve(it) }

fun main() {
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 357)
    check(part2(testInput) == 3121910778619L)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
