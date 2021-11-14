package com.tiwalasautak.rng.grid

class GridCoordinates(private val rowSize: Int) {
    fun linearAddressToPointOnGrid(linearAddress: Int): Point2D {
        val row = (linearAddress - 1) / rowSize
        val column = (linearAddress - 1) % rowSize
        return Point2D(x = column, y = row)
    }
}