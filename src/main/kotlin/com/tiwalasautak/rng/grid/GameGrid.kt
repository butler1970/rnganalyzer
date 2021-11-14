package com.tiwalasautak.rng.grid

class GameGrid(

): AbstractGrid<GridCell>(start = 1, end = 80, rowSize = 10) {
    fun selectNumber(number: Int) {
        getGridData(number).selected = true
    }

    fun pickNumber(number: Int) {
        getGridData(number).picked = true
    }

    override fun buildGridCell(index: Int): GridCell {
        return GridCell(index = index)
    }
}