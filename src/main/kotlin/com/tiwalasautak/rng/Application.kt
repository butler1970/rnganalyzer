package com.tiwalasautak.rng

import com.tiwalasautak.rng.ansi.AnsiCursor
import com.tiwalasautak.rng.console.Command
import com.tiwalasautak.rng.console.CommandType
import com.tiwalasautak.rng.console.Input
import com.tiwalasautak.rng.game.GameSimulator
import com.tiwalasautak.rng.game.GameState
import com.tiwalasautak.rng.game.Render
import com.tiwalasautak.rng.util.hasParameter
import com.tiwalasautak.rng.util.twoDecimals
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.ZoneOffset
import kotlin.system.exitProcess

class Application(
    now: LocalDateTime ,
    zoneOffset: ZoneOffset
) {
    private val render: Render = Render()
    private val rngAnalyzer: RNGAnalyzer = RNGAnalyzer(now = now, zoneOffset = zoneOffset)
    private val input: Input = Input()

    companion object {
        private val defaultStartingNumbers = listOf(1, 2, 3, 4, 5)

        @JvmStatic
        fun main(args: Array<String>) {
            val startingNumbers =
                args.hasParameter("numbers")?.let { numbers -> numbers.split(",").mapNotNull { it.toIntOrNull() } }
                    ?: defaultStartingNumbers

            val seedDateTime = args.hasParameter("seed")?.let { LocalDateTime.parse(it) } ?: LocalDateTime.now()
            val seedZoneOffset = args.hasParameter("offset")?.let { ZoneOffset.of(it) } ?: ZoneOffset.of("-08:00")

            Application(seedDateTime, seedZoneOffset).run(startingNumbers)
        }
    }

    fun run(startingNumbers: List<Int>) {
        var iterations = 0
        var lastNumbersPicked = startingNumbers
        var lastCommand = Command(type = CommandType.INVALID)

        val simulator = GameSimulator(
            initialFunds = 20.twoDecimals(),
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

            val gameState = simulator.nextBet(numbers ?: lastNumbersPicked, BigDecimal.valueOf(.25))

            when (gameState) {
                GameState.WINNER -> {
                    val cost = (iterations * .25).twoDecimals()

                    println("Winner!  Found winning numbers after $iterations iteration(s)")
                    println("Estimated cost = \$$cost")
                    println("Funds remaining \$${simulator.fundsRemaining()}")
                }
                GameState.FUNDS_AVAILABLE -> {
                    println("Funds remaining \$${simulator.fundsRemaining()}")
                }
                GameState.GAME_OVER -> {
                    println("Game Over!  After $iterations iteration(s)")
                }
            }

            iterations++

        } while (gameState == GameState.FUNDS_AVAILABLE)
    }
}