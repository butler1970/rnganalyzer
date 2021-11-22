package com.tiwalasautak.rng

import java.time.ZonedDateTime

class RNGAnalyzer(
    now: ZonedDateTime = ZonedDateTime.now(),
    private val from: Int = 1,
    private val to: Int = 80,
    private val numberOfSelections: Int = 20,
    private val selectionBufferSize: Int = 100
) {
    private val rng: RandomNumberGenerator = RandomNumberGenerator(now = now)
    private val numbersPicked = mutableMapOf<Int, Int>()
    private val allSelections = mutableListOf<List<Int>>()

    init {
        bufferSelections()
    }

    fun getCurrentSelection(): List<Int> {
        return allSelections[0]
    }

    fun getNextSelection(): List<Int> {
        return allSelections[1]
    }

    fun advanceSelection() {
        allSelections.removeAt(0)

        if (allSelections.count() < 2) {
            bufferSelections()
        }
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

    private fun bufferSelections() {
        repeat(selectionBufferSize) {
            allSelections.add(generateSelections(from, to, numberOfSelections))
        }
    }

    private fun generateSelections(from: Int, to: Int, numberOfSelections: Int): List<Int> {
        val result = rng.generateSelections(from, to, numberOfSelections)

        result.forEach { selection ->
            numbersPicked[selection] = numbersPicked[selection]?.let { it + 1 } ?: 1
        }

        return result
    }

}