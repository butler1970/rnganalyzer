package com.tiwalasautak.rng.console

data class Command(
    val type: CommandType,
    val numbers: List<Int>? = null
)