package com.tiwalasautak.rng.grid

abstract class AbstractGrid<T>(
    private val start: Int,
    private val end: Int,
    private val rowSize: Int
) {
    protected fun getGridData(number: Int, grid: List<List<T>>): T {
        val coordinates = GridCoordinates(rowSize)
        val point = coordinates.linearAddressToPointOnGrid(number)

        return grid[point.y][point.x]
    }

    protected fun buildGrid(): List<List<T>> {
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

    abstract fun buildGridCell(index: Int): T
}