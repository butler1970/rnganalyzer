package com.tiwalasautak.rng

import com.tiwalasautak.rng.ansi.AnsiCursor
import com.tiwalasautak.rng.console.Input
import com.tiwalasautak.rng.util.twoDecimals
import java.math.BigDecimal

class Application() {
    private val render: Render = Render()
    private val rngAnalyzer: RNGAnalyzer = RNGAnalyzer()
    private val input: Input = Input()

    companion object {
        private val winningNumbers = listOf(1, 2, 3, 4, 5)

        @JvmStatic
        fun main(args: Array<String>) {
            Application().run()
        }
    }

    fun run() {
        var iterations = 0
        var lastNumbersPicked = winningNumbers
        var lastCommand = Input.Command(type = Input.CommandType.INVALID)

        val simulator = GameSimulator(
            initialFunds = 20.twoDecimals(),
            winningNumbers = winningNumbers,
            rngAnalyzer = rngAnalyzer,
            render = render
        )

        print(AnsiCursor.clearScreen)

        do {
            val command = if (lastCommand.type != Input.CommandType.LET_IT_RIDE) {
                input.getInput(lastNumbersPicked)
            } else {
                lastCommand
            }

            lastCommand = command

            val numbers = when(command.type) {
                Input.CommandType.LIST_OF_NUMBERS -> {
                    if (command.numbers?.count() in 4..6) {
                        lastNumbersPicked = command.numbers ?: listOf()
                        command.numbers
                    } else {
                        lastNumbersPicked
                    }
                }
                Input.CommandType.LET_IT_RIDE -> {
                    lastNumbersPicked
                }
                Input.CommandType.QUIT -> {
                    break
                }
                Input.CommandType.INVALID -> {
                    println("Invalid command! Exiting now.")
                    break
                }
            }

            val gameState = simulator.nextBet(numbers ?: lastNumbersPicked, BigDecimal.valueOf(.25))

            when (gameState) {
                GameSimulator.GameState.WINNER -> {
                    val cost = (iterations * .25).twoDecimals()

                    println("Winner!  Found winning numbers after $iterations iteration(s)")
                    println("Estimated cost = \$$cost")
                    println("Funds remaining \$${simulator.fundsRemaining()}")
                }
                GameSimulator.GameState.FUNDS_AVAILABLE -> {
                    println("Funds remaining \$${simulator.fundsRemaining()}")
                }
                GameSimulator.GameState.GAME_OVER -> {
                    println("\n\nGame Over!  After $iterations iteration(s)")
                }
            }

            iterations++

        } while (gameState == GameSimulator.GameState.FUNDS_AVAILABLE)
    }
}