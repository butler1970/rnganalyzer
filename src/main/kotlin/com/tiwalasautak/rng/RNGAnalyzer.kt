package com.tiwalasautak.rng

import java.time.LocalDateTime
import java.time.ZoneOffset

class RNGAnalyzer(
    now: LocalDateTime = LocalDateTime.now(),
    zoneOffset: ZoneOffset = ZoneOffset.of("-08:00")
) {
    private val rng: RandomNumberGenerator = RandomNumberGenerator(now = now, zoneOffset = zoneOffset)
    private val numbersPicked = mutableMapOf<Int, Int>()

    fun generateSelections(from: Int, to: Int, numberOfSelections: Int): List<Int> {
        val result = rng.generateSelections(from, to, numberOfSelections)

        result.forEach { selection ->
            numbersPicked[selection] = numbersPicked[selection]?.let { it + 1 } ?: 1
        }

        return result
    }

    fun crossSection(desiredNumbers: List<Int>, selections: List<Int>): Int {
        var desiredNumbersFound = 0

        desiredNumbers.forEach {
            if (selections.contains(it)) {
                desiredNumbersFound++
            }
        }

        return desiredNumbersFound
    }

    fun getNumbersPicked(): Map<Int, Int> {
        return numbersPicked
    }
}