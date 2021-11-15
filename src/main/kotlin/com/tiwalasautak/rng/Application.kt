package com.tiwalasautak.rng

import com.tiwalasautak.rng.console.Command
import com.tiwalasautak.rng.console.CommandType
import com.tiwalasautak.rng.console.Input
import com.tiwalasautak.rng.game.GameSimulator
import com.tiwalasautak.rng.console.Render
import com.tiwalasautak.rng.game.GameState
import com.tiwalasautak.rng.util.hasParameter
import com.tiwalasautak.rng.util.hasSwitch
import com.tiwalasautak.rng.util.twoDecimals
import java.math.BigDecimal
import java.time.ZonedDateTime
import kotlin.system.exitProcess

class Application {
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
                Command(type = CommandType.INITIAL)
            }
            val withDelay = args.hasParameter("delay")?.toLongOrNull()
            val initialFunds = args.hasParameter("funds")?.toBigDecimal()?.twoDecimals() ?: 20.twoDecimals()
            val silentMode = args.hasSwitch("silent")
            val continuous = args.hasSwitch("continuous")

            Application().run(
                now = seedDateTime,
                initialCommand = initialCommand,
                withDelay = withDelay,
                initialFunds = initialFunds,
                startingNumbers = startingNumbers,
                silentMode = silentMode,
                continuous = continuous
            )
        }
    }

    fun run(
        now: ZonedDateTime,
        initialCommand: Command,
        withDelay: Long?,
        initialFunds: BigDecimal,
        startingNumbers: List<Int>,
        silentMode: Boolean,
        continuous: Boolean
    ) {
        if (silentMode) {
            println("Seed date/time = $now")
        }

        val render = Render(silentMode = silentMode)
        val input = Input(silentMode = silentMode, render = render)
        val rngAnalyzer = RNGAnalyzer(now = now)
        var gamesPlayed = 0

        do {
            val simulator = GameSimulator(
                initialFunds = initialFunds,
                now = now,
                rngAnalyzer = rngAnalyzer,
                render = render,
                input = input
            )

            val result = simulator.run(initialCommand = initialCommand, withDelay = withDelay, startingNumbers = startingNumbers)

            gamesPlayed++

        } while(result == GameState.GAME_OVER && continuous)

        if (continuous) {
            val spent = gamesPlayed.twoDecimals() * initialFunds.twoDecimals()
            println("Winner! After $gamesPlayed game(s) played.  Spent \$${spent.twoDecimals()}")
        }

        exitProcess(0)
    }
}