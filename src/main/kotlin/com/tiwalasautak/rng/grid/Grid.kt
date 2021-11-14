package com.tiwalasautak.rng.grid

import com.tiwalasautak.rng.table.Table

class Grid(
    private val start: Int,
    private val end: Int,
    private val rowSize: Int
) {
    private var grid: Array<Array<GridCell>> = arrayOf(arrayOf())

    fun buildGrid() {
        grid = buildGrid(start, end, rowSize)

    }

    fun selectNumber(number: Int) {
        getGridData(number).selected = true
    }

    fun pickNumber(number: Int) {
        getGridData(number).picked = true
    }

    fun renderGrid() {
        renderGrid(grid)
    }

    private fun buildGrid(start: Int, end: Int, rowSize: Int): Array<Array<GridCell>> {
        val result = mutableListOf<Array<GridCell>>()

        for (i in start..end step rowSize) {
            result.add(buildRow(i, i + (rowSize - 1)))
        }

        return result.toTypedArray()
    }


    private fun renderGrid(grid: Array<Array<GridCell>>) {
        val table = Table()

        grid.forEach { gridRow ->
            table.addRow(gridRow.map { it.toString() }.toTypedArray())
        }

        println(table.buildTable())
    }

    private fun buildRow(start: Int, end: Int): Array<GridCell> {
        val result = mutableListOf<GridCell>()

        for (i in start..end) {
            result.add(GridCell(i))
        }

        return result.toTypedArray()
    }

    private fun getGridData(number: Int): GridCell {
        val coordinates = Coordinates(rowSize)
        val point = coordinates.linearAddressToPointOnGrid(number)

        return grid[point.y][point.x]
    }
}