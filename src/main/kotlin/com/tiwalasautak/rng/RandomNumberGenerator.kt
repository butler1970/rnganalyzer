package com.tiwalasautak.rng

import java.time.LocalDateTime
import java.time.ZoneOffset
import kotlin.random.Random

class RandomNumberGenerator {
    private val rng: Random

    init {
//        val now = LocalDateTime.parse("2021-07-15T19:00:00")
        val now = LocalDateTime.now()
        val seed = now.toEpochSecond(ZoneOffset.UTC)

        println("Seed = $seed\n\n")

        rng = Random(seed)
    }

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