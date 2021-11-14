package com.tiwalasautak.rng.game

import com.tiwalasautak.rng.RNGAnalyzer
import com.tiwalasautak.rng.ansi.AnsiCursor
import com.tiwalasautak.rng.util.twoDecimals
import java.math.BigDecimal

class GameSimulator(
    initialFunds: BigDecimal = 20.twoDecimals(),
    private val rngAnalyzer: RNGAnalyzer,
    private val render: Render
) {
    private var funds = initialFunds
    private var payouts: Payouts = Payouts()

    init {
        print(AnsiCursor.clearScreen)
    }

    fun nextBet(numbers: List<Int>, bet: BigDecimal): GameState {
        print(AnsiCursor.clearScreen)

        placeBet(bet)

        val selections = rngAnalyzer.generateSelections(1, 80, 20)
        val crossSection = rngAnalyzer.crossSection(numbers, selections)

        calculateRemainingFunds(numbers.count(), crossSection)

        println("\n")
        render.populateGridAndRender(numbers, selections)
        render.renderCrossSection(numbers.count(), crossSection)
        render.renderNumbers(numbers)
        render.renderSelections(selections)
        render.renderNumbersPicked(rngAnalyzer.getNumbersPicked())

        return when {
            crossSection == numbers.count() -> GameState.WINNER
            isGameOver() -> GameState.GAME_OVER
            else -> GameState.FUNDS_AVAILABLE
        }
    }

    fun fundsRemaining(): BigDecimal {
        return funds
    }

    private fun placeBet(bet: BigDecimal): BigDecimal {
        funds -= bet
        return funds
    }

    private fun isGameOver(): Boolean {
        return funds.twoDecimals() == BigDecimal.ZERO.twoDecimals()
    }

    private fun calculateRemainingFunds(numbers: Int, crossSection: Int): BigDecimal {
        val payout = when (numbers) {
            4 -> payouts.calculatePayoutFor4Spot(crossSection)
            5 -> payouts.calculatePayoutFor5Spot(crossSection)
            6 -> payouts.calculatePayoutFor6Spot(crossSection)
            else -> throw Exception("$numbers not supported!")
        }

        funds += payout

        return funds
    }
}