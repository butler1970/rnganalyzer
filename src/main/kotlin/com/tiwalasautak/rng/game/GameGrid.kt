package com.tiwalasautak.rng.game

import com.jakewharton.picnic.Cell
import com.jakewharton.picnic.CellStyle
import com.jakewharton.picnic.Row
import com.jakewharton.picnic.TextAlignment
import com.tiwalasautak.rng.grid.AbstractGrid
import com.tiwalasautak.rng.table.Table

class GameGrid: AbstractGrid<GameGridCell>(start = 1, end = 80, rowSize = 10) {
    private val gridA: List<List<GameGridCell>> = buildGrid()
    private val gridB: List<List<GameGridCell>> = buildGrid()

    fun renderGrid(): String {
        return renderGrid(gridA, gridB)
    }

    fun selectNumberA(number: Int) {
        getGridData(number, gridA).selected = true
    }

    fun selectNumberB(number: Int) {
        getGridData(number, gridB).selected = true
    }

    fun pickNumber(number: Int) {
        getGridData(number, gridA).picked = true
    }

    override fun buildGridCell(index: Int): GameGridCell {
        return GameGridCell(index = index)
    }

    private fun renderGrid(gridA: List<List<GameGridCell>>, gridB: List<List<GameGridCell>>): String {
        val columnCount = gridA[0].count()
        val table = Table()

        table.addRow(Row {
            addCell(Cell("Current Hand") {
                columnSpan = columnCount
                style = CellStyle {
                    alignment = TextAlignment.MiddleCenter
                }
            })

            addCell(Cell(" ") {
                style = CellStyle {
                    borderTop = false
                    borderBottom = false
                }
            })

            addCell(Cell("Next Hand") {
                columnSpan = columnCount
                style = CellStyle {
                    alignment = TextAlignment.MiddleCenter
                }
            })
        })

        gridA.forEachIndexed { index, value ->
            table.addRow(
                gridACells = value.map { it.toString() }.toTypedArray(),
                gridBCells = gridB[index].map { it.toString() }.toTypedArray()
            )
        }

        return table.buildTable()
    }
}