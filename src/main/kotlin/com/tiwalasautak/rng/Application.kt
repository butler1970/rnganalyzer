package com.tiwalasautak.rng

import com.tiwalasautak.rng.console.Command
import com.tiwalasautak.rng.console.CommandType
import com.tiwalasautak.rng.console.Input
import com.tiwalasautak.rng.game.GameSimulator
import com.tiwalasautak.rng.console.Render
import com.tiwalasautak.rng.util.hasParameter
import com.tiwalasautak.rng.util.hasSwitch
import com.tiwalasautak.rng.util.twoDecimals
import java.math.BigDecimal
import java.time.ZonedDateTime

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
            val withDelay = args.hasSwitch("delay")
            val initialFunds = args.hasParameter("funds")?.toBigDecimal()?.twoDecimals() ?: 20.twoDecimals()

            Application().run(
                now = seedDateTime,
                initialCommand = initialCommand,
                withDelay = withDelay,
                initialFunds = initialFunds,
                startingNumbers = startingNumbers
            )
        }
    }

    fun run(now: ZonedDateTime, initialCommand: Command, withDelay: Boolean, initialFunds: BigDecimal, startingNumbers: List<Int>) {
        val silentMode = false
        val render = Render(silentMode)
        val simulator = GameSimulator(
            initialFunds = initialFunds,
            now = now,
            rngAnalyzer = RNGAnalyzer(now = now),
            render = render,
            input = Input(silentMode, render)
        )

        simulator.run(initialCommand = initialCommand, withDelay = withDelay, startingNumbers = startingNumbers)
    }
}