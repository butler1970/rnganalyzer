package com.tiwalasautak.rng.game

import com.tiwalasautak.rng.grid.GameGrid
import com.tiwalasautak.rng.util.twoDecimals
import java.math.BigDecimal

class Render {
    fun populateGridAndRender(numbersPicked: List<Int>, selections: List<Int>) {
        val grid = GameGrid()

        numbersPicked.forEach {
            grid.pickNumber(it)
        }

        selections.forEach {
            grid.selectNumber(it)
        }

        grid.renderGrid()
    }

    fun renderNumbersPicked(numbersPicked: Map<Int, Int>) {
        println("Top picked numbers:\n")

        numbersPicked.flatMap {
            listOf(Pair(it.key, it.value))
        }.sortedByDescending {
            it.second
        }.chunked(10).forEach { listOfPairs ->
            listOfPairs.forEach {
                print("${it.first} = ${it.second}".padEnd(10, ' '))
            }
            print("\n")
        }

        println("\n")
    }

    fun renderNumbers(numbers: List<Int>) {
        println("Numbers picked: ${numbers.joinToString()}\n")
    }

    fun renderCrossSection(numbers: Int, crossSection: Int, payout: BigDecimal) {
        println("$crossSection out of $numbers found!  Payout: \$$payout\n")
    }

    fun renderSelections(selections: List<Int>) {
        println("Selections: ${selections.sorted().joinToString()}\n")
    }

    fun renderWinnerMessage(fundsRemaining: BigDecimal, iterations: Int) {
        val cost = (iterations * .25).twoDecimals()

        println("Winner!  Found winning numbers after $iterations iteration(s)")
        println("Estimated cost = \$$cost")
        println("Funds remaining \$$fundsRemaining")
    }

    fun renderFundsRemaining(fundsRemaining: BigDecimal) {
        println("Funds remaining \$$fundsRemaining")
    }

    fun renderGameOver(iterations: Int) {
        println("Game Over!  After $iterations iteration(s)")
    }
}