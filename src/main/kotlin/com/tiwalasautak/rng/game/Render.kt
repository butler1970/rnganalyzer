package com.tiwalasautak.rng.game

import com.tiwalasautak.rng.grid.Grid

class Render {
    private val grid: Grid = Grid(start = 1, end = 80, rowSize = 10)

    fun populateGridAndRender(numbersPicked: List<Int>, selections: List<Int>) {
        grid.buildGrid()

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

    fun renderCrossSection(numbers: Int, crossSection: Int) {
        println("$crossSection out of $numbers found!\n")
    }

    fun renderSelections(selections: List<Int>) {
        println("Selections: ${selections.sorted().joinToString()}\n")
    }
}