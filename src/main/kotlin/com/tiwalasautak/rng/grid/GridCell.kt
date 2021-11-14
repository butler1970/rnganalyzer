package com.tiwalasautak.rng.grid

import com.tiwalasautak.rng.ansi.AnsiColor.colorText
import com.tiwalasautak.rng.ansi.AnsiColor.greenOnBlue
import com.tiwalasautak.rng.ansi.AnsiColor.redOnBlue
import com.tiwalasautak.rng.ansi.AnsiColor.whiteOnBlue
import com.tiwalasautak.rng.ansi.AnsiColor.yellowOnBlue
import com.tiwalasautak.rng.ansi.AnsiStyle.bold

data class GridCell(
    val index: Int,
    var selected: Boolean = false,
    var picked: Boolean = false
) {
    override fun toString(): String {
        return when {
            !selected && !picked -> colorText(whiteOnBlue + bold, index.toString().padEnd(5, ' '))
            selected && !picked -> colorText(yellowOnBlue + bold, "$index(+)".padEnd(5, ' '))
            selected && picked -> colorText(greenOnBlue + bold,"$index(*)".padEnd(5, ' '))
            !selected && picked -> colorText(redOnBlue + bold,"$index(-)".padEnd(5, ' '))
            else -> index.toString()
        }
    }
}