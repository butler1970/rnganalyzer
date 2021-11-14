package com.tiwalasautak.rng.game

import com.tiwalasautak.rng.grid.AbstractGrid
import com.tiwalasautak.rng.table.Table

class GameGrid: AbstractGrid<GridCell>(start = 1, end = 80, rowSize = 10) {
    private val grid: List<List<GridCell>> = buildGrid()

    fun renderGrid() {
        renderGrid(grid)
    }

    fun selectNumber(number: Int) {
        getGridData(number, grid).selected = true
    }

    fun pickNumber(number: Int) {
        getGridData(number, grid).picked = true
    }

    override fun buildGridCell(index: Int): GridCell {
        return GridCell(index = index)
    }

    private fun renderGrid(grid: List<List<GridCell>>) {
        val table = Table()

        grid.forEach { gridRow ->
            table.addRow(gridRow.map { it.toString() }.toTypedArray())
        }

        println(table.buildTable())
    }
}