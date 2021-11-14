package com.tiwalasautak.rng.grid

import com.tiwalasautak.rng.ansi.AnsiColor.bgBlue
import com.tiwalasautak.rng.ansi.AnsiColor.bgGreen
import com.tiwalasautak.rng.ansi.AnsiColor.bgRed
import com.tiwalasautak.rng.ansi.AnsiColor.bgYellow
import com.tiwalasautak.rng.ansi.AnsiColor.colorText
import com.tiwalasautak.rng.ansi.AnsiColor.fgBlack
import com.tiwalasautak.rng.ansi.AnsiColor.fgWhite
import com.tiwalasautak.rng.ansi.AnsiColor.on
import com.tiwalasautak.rng.ansi.AnsiStyle.bold

data class GridCell(
    val index: Int,
    var selected: Boolean = false,
    var picked: Boolean = false
) {
    companion object {
        private val columnWidth = 4
        private val styleDefault = (fgWhite on bgBlue) + bold
        private val stylePicked = (fgBlack on bgRed) + bold
        private val styleSelected = (fgBlack on bgYellow) + bold
        private val stylePickedAndSelected = (fgBlack on bgGreen) + bold
    }

    private fun getValueAsString(): String {
        return " " + index.toString().padStart(2, ' ') + " "
    }

    override fun toString(): String {
        return when {
            !selected && !picked -> colorText(styleDefault, getValueAsString())
            selected && !picked -> colorText(styleSelected, getValueAsString())
            selected && picked -> colorText(stylePickedAndSelected, getValueAsString())
            !selected && picked -> colorText(stylePicked, getValueAsString())
            else -> colorText(styleDefault, getValueAsString())
        }
    }
}