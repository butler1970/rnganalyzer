package com.tiwalasautak.rng.console

import com.tiwalasautak.rng.ansi.AnsiCursor

class Input {
    fun getInput(lastNumbersPicked: List<Int>): Command {
        print(AnsiCursor.topLeft)
        print("Pick numbers [${lastNumbersPicked.joinToString()}]:")

        val input = readLine()

        input?.let { nonNullInput ->
            return when(nonNullInput.lowercase()) {
                "auto" -> {
                    Command(type = CommandType.AUTO)
                }
                "quit" -> {
                    Command(type = CommandType.QUIT)
                }
                else -> {
                    Command(
                        type = CommandType.NUMBERS,
                        numbers = nonNullInput.split(",").mapNotNull { it.trim().toIntOrNull() }
                    )
                }
            }
        }

        return Command(type = CommandType.INVALID)
    }
}