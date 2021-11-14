package com.tiwalasautak.rng.game

import com.tiwalasautak.rng.ansi.AnsiCursor
import com.tiwalasautak.rng.grid.Grid

class Render {
    private val grid: Grid = Grid(start = 1, end = 80, rowSize = 10)

    fun renderNumbers(numbers: List<Int>) {
        println("\n\nNumbers picked: ${numbers.joinToString()}")
    }

    fun renderCrossSection(numbers: Int, crossSection: Int) {
        println("\n\n$crossSection out of $numbers found!")
    }

    fun populateGridAndRender(numbersPicked: List<Int>, selections: List<Int>) {
        grid.buildGrid()

        numbersPicked.forEach {
            grid.pickNumber(it)
        }

        selections.forEach {
            grid.selectNumber(it)
        }

        print(AnsiCursor.clearScreen)
        renderSelections(selections)
        grid.renderGrid()
    }

    fun renderNumbersPicked(numbersPicked: Map<Int, Int>, numbersSelected: Int) {
        println("\n\nTop picked numbers:")

        numbersPicked.flatMap {
            listOf(Pair(it.key, it.value))
        }.sortedByDescending {
            it.second
        }.take(
            numbersSelected
        ).forEach {
            println("${it.first} = ${it.second}")
        }
    }

    private fun renderSelections(selections: List<Int>) {
        println("\n\nSelections: ${selections.sorted().joinToString()}")
    }

}