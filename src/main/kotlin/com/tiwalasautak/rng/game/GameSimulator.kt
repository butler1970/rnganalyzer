package com.tiwalasautak.rng.game

import com.tiwalasautak.rng.RNGAnalyzer
import com.tiwalasautak.rng.ansi.AnsiCursor
import com.tiwalasautak.rng.console.Command
import com.tiwalasautak.rng.console.CommandType
import com.tiwalasautak.rng.console.Input
import com.tiwalasautak.rng.console.Render
import com.tiwalasautak.rng.util.twoDecimals
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.math.BigDecimal
import java.time.ZonedDateTime

class GameSimulator(
    initialFunds: BigDecimal,
    private val now: ZonedDateTime,
    private val rngAnalyzer: RNGAnalyzer,
    private val render: Render,
    private val input: Input
) {
    private var funds = initialFunds
    private var payouts: Payouts = Payouts()

    fun run(initialCommand: Command, withDelay: Boolean, startingNumbers: List<Int>) {
        render.clearConsole()

        var iterations = 0
        var lastNumbersPicked = startingNumbers
        var lastCommand = initialCommand

        do {
            val command = getNextCommand(lastCommand = lastCommand, lastNumbersPicked = lastNumbersPicked)

            when (command.type) {
                CommandType.QUIT -> {
                    render.renderQuit()
                    return
                }
                CommandType.INVALID -> {
                    render.renderInvalidCommand()
                    return
                }
                else -> {}
            }

            val numbers = getNumbers(command = command, lastNumbersPicked = lastNumbersPicked)
            val gameState = nextBet(numbers = numbers, bet = .25.twoDecimals())

            when (gameState) {
                GameState.WINNER -> {
                    render.renderWinnerMessage(fundsRemaining(), iterations)
                }
                GameState.FUNDS_AVAILABLE -> {
                    render.renderFundsRemaining(fundsRemaining())
                }
                GameState.GAME_OVER -> {
                    render.renderGameOver(iterations)
                }
            }

            runBlocking {
                if (withDelay) delay(100)
            }

            iterations++
            lastCommand = command
            lastNumbersPicked = numbers

        } while (gameState == GameState.FUNDS_AVAILABLE)
    }

    private fun getNextCommand(lastCommand: Command, lastNumbersPicked: List<Int>): Command {
        return if (lastCommand.type != CommandType.AUTO) {
            input.getInput(lastNumbersPicked)
        } else {
            lastCommand
        }
    }

    private fun getNumbers(command: Command, lastNumbersPicked: List<Int>): List<Int> {
        return when (command.type) {
            CommandType.INITIAL, CommandType.REPEAT -> {
                lastNumbersPicked
            }
            CommandType.NUMBERS -> {
                if (command.numbers?.count() in 4..6) {
                    command.numbers ?: listOf()
                } else {
                    lastNumbersPicked
                }
            }
            CommandType.AUTO -> {
                lastNumbersPicked
            }
            CommandType.QUIT -> {
                lastNumbersPicked
            }
            CommandType.INVALID -> {
                lastNumbersPicked
            }
        }
    }

    private fun nextBet(numbers: List<Int>, bet: BigDecimal): GameState {
        render.render(AnsiCursor.clearScreen)
        render.renderln("\n$now")

        placeBet(bet)

        val selections = rngAnalyzer.generateSelections(1, 80, 20)
        val crossSection = rngAnalyzer.crossSection(numbers, selections)
        val payout = calculatePayout(numbers.count(), crossSection)

        updateFunds(payout)

        render.populateGridAndRender(numbers, selections)
        render.renderCrossSection(numbers.count(), crossSection, payout)
        render.renderNumbers(numbers)
        render.renderSelections(selections)
        render.renderNumbersPicked(rngAnalyzer.getNumbersPicked())

        return when {
            crossSection == numbers.count() -> GameState.WINNER
            isGameOver() -> GameState.GAME_OVER
            else -> GameState.FUNDS_AVAILABLE
        }
    }

    private fun fundsRemaining(): BigDecimal {
        return funds
    }

    private fun placeBet(bet: BigDecimal): BigDecimal {
        funds -= bet
        return funds
    }

    private fun isGameOver(): Boolean {
        return funds.twoDecimals() == BigDecimal.ZERO.twoDecimals()
    }

    private fun calculatePayout(numbers: Int, crossSection: Int): BigDecimal {
        return when (numbers) {
            4 -> payouts.calculatePayoutFor4Spot(crossSection)
            5 -> payouts.calculatePayoutFor5Spot(crossSection)
            6 -> payouts.calculatePayoutFor6Spot(crossSection)
            else -> throw Exception("$numbers not supported!")
        }.twoDecimals()
    }

    private fun updateFunds(payout: BigDecimal): BigDecimal {
        funds += payout
        return funds
    }
}