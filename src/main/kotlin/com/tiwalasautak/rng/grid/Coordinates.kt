package com.tiwalasautak.rng.grid

class Coordinates(private val rowSize: Int) {
    data class Point2D(
        val x: Int,
        val y: Int
    )

    fun linearAddressToPointOnGrid(linearAddress: Int): Point2D {
        val row = (linearAddress - 1) / rowSize
        val column = (linearAddress - 1) % rowSize
        return Point2D(x = column, y = row)
    }
}