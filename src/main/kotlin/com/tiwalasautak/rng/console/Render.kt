package com.tiwalasautak.rng.console

import com.tiwalasautak.rng.ansi.AnsiCursor
import com.tiwalasautak.rng.game.GameGrid
import com.tiwalasautak.rng.util.twoDecimals
import java.math.BigDecimal

class Render(
    private val silentMode: Boolean
) {
    fun clearConsole() {
        render(AnsiCursor.clearScreen)
    }

    fun cursorTopLeft() {
        render(AnsiCursor.topLeft)
    }

    fun populateGridAndRender(numbersPicked: List<Int>, selections: List<Int>) {
        val grid = GameGrid()

        numbersPicked.forEach {
            grid.pickNumber(it)
        }

        selections.forEach {
            grid.selectNumber(it)
        }

        renderln(grid.renderGrid())
    }

    fun renderNumbersPicked(numbersPicked: Map<Int, Int>) {
        renderln("Top picked numbers:\n")

        numbersPicked.flatMap {
            listOf(Pair(it.key, it.value))
        }.sortedByDescending {
            it.second
        }.chunked(10).forEach { listOfPairs ->
            listOfPairs.forEach {
                render("${it.first} = ${it.second}".padEnd(10, ' '))
            }
            render("\n")
        }

        renderln("\n")
    }

    fun renderNumbers(numbers: List<Int>) {
        renderln("Numbers picked: ${numbers.joinToString()}\n")
    }

    fun renderCrossSection(numbers: Int, crossSection: Int, payout: BigDecimal) {
        renderln("$crossSection out of $numbers found!  Payout: \$$payout\n")
    }

    fun renderSelections(selections: List<Int>) {
        renderln("Selections: ${selections.sorted().joinToString()}\n")
    }

    fun renderWinnerMessage(fundsRemaining: BigDecimal, iterations: Int) {
        val cost = (iterations * .25).twoDecimals()

        renderln("Winner!  Found winning numbers after $iterations iteration(s)")
        renderln("Estimated cost = \$$cost")
        renderln("Funds remaining \$$fundsRemaining")
    }

    fun renderFundsRemaining(fundsRemaining: BigDecimal) {
        renderln("Funds remaining \$$fundsRemaining")
    }

    fun renderGameOver(iterations: Int) {
        renderln("Game Over!  After $iterations iteration(s)")
    }

    fun renderInvalidCommand() {
        renderln(AnsiCursor.clearScreen)
        renderln("Invalid command! Exiting now.")
    }

    fun renderQuit() {
        renderln(AnsiCursor.clearScreen)
    }

    fun render(text: String) {
        if (!silentMode) {
            print(text)
        }
    }

    fun renderln(text: String) {
        if (!silentMode) {
            println(text)
        }
    }
}