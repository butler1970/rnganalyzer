package com.tiwalasautak.rng

import java.math.BigDecimal

class Payouts {
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
}