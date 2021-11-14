package com.tiwalasautak.rng

import java.time.ZonedDateTime
import kotlin.random.Random

class RandomNumberGenerator(now: ZonedDateTime = ZonedDateTime.now()) {
    private val rng: Random = Random(now.toEpochSecond())

    fun generateSelections(from: Int, to: Int, numberOfSelections: Int): List<Int> {
        val result = mutableListOf<Int>()

        while (result.count() < numberOfSelections) {
            val selection = rng.nextInt(from, to + 1)

            if (!result.contains(selection)) {
                result.add(selection)
            }
        }

        return result
    }
}