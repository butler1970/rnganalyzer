package com.tiwalasautak.rng.console

import com.tiwalasautak.rng.ansi.AnsiCursor

class Input {
    enum class CommandType { LIST_OF_NUMBERS, LET_IT_RIDE, QUIT, INVALID }

    data class Command(
        val type: CommandType,
        val numbers: List<Int>? = null
    )

    fun getInput(lastNumbersPicked: List<Int>): Command {
        print(AnsiCursor.topLeft)
        print("Pick numbers [${lastNumbersPicked.joinToString()}]:")

        val input = readLine()

        input?.let { nonNullInput ->
            return when(nonNullInput) {
                "let it ride" -> {
                    Command(type = CommandType.LET_IT_RIDE)
                }
                "quit" -> {
                    Command(type = CommandType.QUIT)
                }
                else -> {
                    Command(type = CommandType.LIST_OF_NUMBERS, numbers = nonNullInput.split(",").mapNotNull { it.trim().toIntOrNull() })
                }
            }
        }

        return Command(type = CommandType.INVALID)
    }
}