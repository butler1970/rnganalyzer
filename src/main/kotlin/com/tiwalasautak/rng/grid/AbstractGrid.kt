package com.tiwalasautak.rng.grid

import com.tiwalasautak.rng.table.Table

abstract class AbstractGrid<T>(
    private val start: Int,
    private val end: Int,
    private val rowSize: Int
) {
    private val grid: List<List<T>> = buildGrid()

    fun renderGrid() {
        renderGrid(grid)
    }

    private fun renderGrid(grid: List<List<T>>) {
        val table = Table()

        grid.forEach { gridRow ->
            table.addRow(gridRow.map { it.toString() }.toTypedArray())
        }

        println(table.buildTable())
    }

    private fun buildGrid(): List<List<T>> {
        return buildGrid(start, end, rowSize)
    }

    private fun buildGrid(start: Int, end: Int, rowSize: Int): List<List<T>> {
        val result = mutableListOf<List<T>>()

        for (i in start..end step rowSize) {
            result.add(buildRow(i, i + (rowSize - 1)))
        }

        return result
    }

    private fun buildRow(start: Int, end: Int): List<T> {
        val result = mutableListOf<T>()

        for (i in start..end) {
            result.add(buildGridCell(i))
        }

        return result
    }

    protected fun getGridData(number: Int): T {
        val coordinates = GridCoordinates(rowSize)
        val point = coordinates.linearAddressToPointOnGrid(number)

        return grid[point.y][point.x]
    }

    abstract fun buildGridCell(index: Int): T
}