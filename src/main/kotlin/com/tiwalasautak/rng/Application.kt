package com.tiwalasautak.rng

import com.tiwalasautak.rng.ansi.AnsiCursor
import com.tiwalasautak.rng.console.Command
import com.tiwalasautak.rng.console.CommandType
import com.tiwalasautak.rng.console.Input
import com.tiwalasautak.rng.game.GameSimulator
import com.tiwalasautak.rng.game.GameState
import com.tiwalasautak.rng.game.Render
import com.tiwalasautak.rng.util.hasParameter
import com.tiwalasautak.rng.util.hasSwitch
import com.tiwalasautak.rng.util.twoDecimals
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.ZonedDateTime
import kotlin.system.exitProcess

class Application(private val now: ZonedDateTime) {
    private val render: Render = Render(1, 80, 10)
    private val rngAnalyzer: RNGAnalyzer = RNGAnalyzer(now = now)
    private val input: Input = Input()

    companion object {
        private val defaultStartingNumbers = listOf(1, 2, 3, 4, 5)

        @JvmStatic
        fun main(args: Array<String>) {
            val startingNumbers =
                args.hasParameter("numbers")?.let { numbers -> numbers.split(",").mapNotNull { it.toIntOrNull() } }
                    ?: defaultStartingNumbers

            val seedDateTime = args.hasParameter("seed")?.let { ZonedDateTime.parse(it) } ?: ZonedDateTime.now()
            val initialCommand = if (args.hasSwitch("auto")) {
                Command(type = CommandType.AUTO)
            } else {
                Command(type = CommandType.INVALID)
            }
            val withDelay = args.hasSwitch("delay")
            val initialFunds = args.hasParameter("funds")?.toBigDecimal()?.twoDecimals() ?: 20.twoDecimals()

            Application(seedDateTime).run(initialCommand, withDelay, initialFunds, startingNumbers)
        }
    }

    fun run(initialCommand: Command, withDelay: Boolean, initialFunds: BigDecimal, startingNumbers: List<Int>) {
        var iterations = 0
        var lastNumbersPicked = startingNumbers
        var lastCommand = initialCommand

        val simulator = GameSimulator(
            initialFunds = initialFunds,
            now = now,
            rngAnalyzer = rngAnalyzer,
            render = render
        )

        do {
            val command = if (lastCommand.type != CommandType.AUTO) {
                input.getInput(lastNumbersPicked)
            } else {
                lastCommand
            }

            lastCommand = command

            val numbers = when (command.type) {
                CommandType.NUMBERS -> {
                    if (command.numbers?.count() in 4..6) {
                        lastNumbersPicked = command.numbers ?: listOf()
                        command.numbers
                    } else {
                        lastNumbersPicked
                    }
                }
                CommandType.AUTO -> {
                    lastNumbersPicked
                }
                CommandType.QUIT -> {
                    println(AnsiCursor.clearScreen)
                    exitProcess(0)
                }
                CommandType.INVALID -> {
                    println(AnsiCursor.clearScreen)
                    println("Invalid command! Exiting now.")
                    exitProcess(0)
                }
            }

            val gameState = simulator.nextBet(numbers = numbers ?: lastNumbersPicked, bet = .25.twoDecimals())

            when (gameState) {
                GameState.WINNER -> {
                    render.renderWinnerMessage(simulator.fundsRemaining(), iterations)
                }
                GameState.FUNDS_AVAILABLE -> {
                    render.renderFundsRemaining(simulator.fundsRemaining())
                }
                GameState.GAME_OVER -> {
                    render.renderGameOver(iterations)
                }
            }

            iterations++

            runBlocking {
                if (withDelay) delay(100)
            }

        } while (gameState == GameState.FUNDS_AVAILABLE)
    }
}