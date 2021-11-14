package com.tiwalasautak.rng.console

import com.tiwalasautak.rng.ansi.AnsiCursor

class Input {
    fun getInput(lastNumbersPicked: List<Int>): Command {
        print(AnsiCursor.topLeft)
        print("Pick numbers [${lastNumbersPicked.joinToString()}]:")

        val input = readLine()?.trim()?.lowercase()

        input?.let { nonNullInput ->
            return when {
                nonNullInput.isEmpty() -> {
                    Command(type = CommandType.REPEAT)
                }
                nonNullInput == "auto" -> {
                    Command(type = CommandType.AUTO)
                }
                nonNullInput == "quit" -> {
                    Command(type = CommandType.QUIT)
                }
                "^\\d+\\s?(,\\s?\\d+\\s?)*$".toRegex().matches(nonNullInput) -> {
                    Command(
                        type = CommandType.NUMBERS,
                        numbers = nonNullInput.split(",").mapNotNull { it.trim().toIntOrNull() }.distinct()
                            .filter { it in 1..80 }
                    )
                }
                else -> {
                    Command(type = CommandType.INVALID)
                }
            }
        }

        return Command(type = CommandType.INVALID)
    }
}