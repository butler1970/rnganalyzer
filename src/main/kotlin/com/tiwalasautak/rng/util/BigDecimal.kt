package com.tiwalasautak.rng.util

import java.math.BigDecimal
import java.math.RoundingMode

fun BigDecimal.twoDecimals(roundingMode: RoundingMode = RoundingMode.HALF_UP): BigDecimal {
    return this.setScale(2, roundingMode)
}

fun Double.twoDecimals(roundingMode: RoundingMode = RoundingMode.HALF_UP): BigDecimal {
    return this.toBigDecimal().twoDecimals(roundingMode)
}

fun Long.twoDecimals(roundingMode: RoundingMode = RoundingMode.HALF_UP): BigDecimal {
    return this.toBigDecimal().twoDecimals(roundingMode)
}

fun Int.twoDecimals(roundingMode: RoundingMode = RoundingMode.HALF_UP): BigDecimal {
    return this.toBigDecimal().twoDecimals(roundingMode)
}

fun Float.twoDecimals(roundingMode: RoundingMode = RoundingMode.HALF_UP): BigDecimal {
    return this.toBigDecimal().twoDecimals(roundingMode)
}
