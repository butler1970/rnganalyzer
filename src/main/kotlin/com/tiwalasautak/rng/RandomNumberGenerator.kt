package com.tiwalasautak.rng

import java.time.LocalDateTime
import java.time.ZoneOffset
import kotlin.random.Random

class RandomNumberGenerator(
    now: LocalDateTime = LocalDateTime.now(),
    zoneOffset: ZoneOffset = ZoneOffset.of("-08:00")
) {
    private val rng: Random = Random(now.toEpochSecond(zoneOffset))

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