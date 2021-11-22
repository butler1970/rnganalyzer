package com.tiwalasautak.rng.game

import java.math.BigDecimal

class Payouts {
    fun calculatePayoutFor3Spot(crossSection: Int): BigDecimal {
        return when (crossSection) {
            0 -> BigDecimal.ZERO
            1 -> BigDecimal.ZERO
            2 -> BigDecimal.valueOf(.50)
            3 -> BigDecimal.valueOf(10.00)
            else -> throw Exception("Can't handle cross section of $crossSection!")
        }
    }

    fun calculatePayoutFor4Spot(crossSection: Int): BigDecimal {
        return when (crossSection) {
            0 -> BigDecimal.ZERO
            1 -> BigDecimal.ZERO
            2 -> BigDecimal.valueOf(.50)
            3 -> BigDecimal.valueOf(1.25)
            4 -> BigDecimal.valueOf(20.00)
            else -> throw Exception("Can't handle cross section of $crossSection!")
        }
    }

    fun calculatePayoutFor5Spot(crossSection: Int): BigDecimal {
        return when (crossSection) {
            0 -> BigDecimal.ZERO
            1 -> BigDecimal.ZERO
            2 -> BigDecimal.ZERO
            3 -> BigDecimal.valueOf(.75)
            4 -> BigDecimal.valueOf(3.00)
            5 -> BigDecimal.valueOf(200.00)
            else -> throw Exception("Can't handle cross section of $crossSection!")
        }
    }

    fun calculatePayoutFor6Spot(crossSection: Int): BigDecimal {
        return when (crossSection) {
            0 -> BigDecimal.ZERO
            1 -> BigDecimal.ZERO
            2 -> BigDecimal.ZERO
            3 -> BigDecimal.valueOf(.75)
            4 -> BigDecimal.valueOf(3.00)
            5 -> BigDecimal.valueOf(20.00)
            6 -> BigDecimal.valueOf(400.00)
            else -> throw Exception("Can't handle cross section of $crossSection!")
        }
    }

    fun calculatePayoutFor7Spot(crossSection: Int): BigDecimal {
        return when (crossSection) {
            0 -> BigDecimal.ZERO
            1 -> BigDecimal.ZERO
            2 -> BigDecimal.ZERO
            3 -> BigDecimal.valueOf(.25)
            4 -> BigDecimal.valueOf(.50)
            5 -> BigDecimal.valueOf(20.00)
            6 -> BigDecimal.valueOf(100.00)
            7 -> BigDecimal.valueOf(1750.00)
            else -> throw Exception("Can't handle cross section of $crossSection!")
        }
    }

    fun calculatePayoutFor8Spot(crossSection: Int): BigDecimal {
        return when (crossSection) {
            0 -> BigDecimal.ZERO
            1 -> BigDecimal.ZERO
            2 -> BigDecimal.ZERO
            3 -> BigDecimal.ZERO
            4 -> BigDecimal.valueOf(.25)
            5 -> BigDecimal.valueOf(.50)
            6 -> BigDecimal.valueOf(20.00)
            7 -> BigDecimal.valueOf(400.00)
            8 -> BigDecimal.valueOf(2500.00)
            else -> throw Exception("Can't handle cross section of $crossSection!")
        }
    }
}