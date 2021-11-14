package com.tiwalasautak.rng.ansi

object AnsiGeneral {
    const val reset = "\u001B[0m"
}

object AnsiCursor {
    const val clearScreen = "\u001B[0;0H\u001B[0J"
    const val topLeft = "\u001B[0;0H"
}

object AnsiStyle {
    const val bold = "\u001B[1m"
}

object AnsiColor {
    const val fgBlack = 30
    const val fgRed = 31
    const val fgGreen = 32
    const val fgYellow = 33
    const val fgBlue = 34
    const val fgMagenta = 35
    const val fgCyan = 36
    const val fgWhite = 37

    const val fgBrightBlack = 90
    const val fgBrightRed = 91
    const val fgBrightGreen = 92
    const val fgBrightYellow = 93
    const val fgBrightBlue = 94
    const val fgBrightMagenta = 95
    const val fgBrightCyan = 96
    const val fgBrightWhite = 97

    const val bgBlack = 40
    const val bgRed = 41
    const val bgGreen = 42
    const val bgYellow = 43
    const val bgBlue = 44
    const val bgMagenta = 45
    const val bgCyan = 46
    const val bgWhite = 47

    const val bgBrightBlack = 100
    const val bgBrightRed = 101
    const val bgBrightGreen = 102
    const val bgBrightYellow = 103
    const val bgBrightBlue = 104
    const val bgBrightMagenta = 105
    const val bgBrightCyan = 106
    const val bgBrightWhite = 107

    const val black = "\u001B[30m"
    const val red = "\u001B[31m"
    const val green = "\u001B[32m"
    const val yellow = "\u001B[33m"
    const val blue = "\u001B[34m"
    const val purple = "\u001B[35m"
    const val cyan = "\u001B[36m"
    const val white = "\u001B[37m"

    const val brightBlack = "\u001B[90m"
    const val brightRed = "\u001B[91m"
    const val brightGreen = "\u001B[92m"
    const val brightYellow = "\u001B[93m"
    const val brightBlue = "\u001B[94m"
    const val brightPurple = "\u001B[95m"
    const val brightCyan = "\u001B[96m"
    const val brightWhite = "\u001B[97m"

    infix fun Int.on(color: Int): String {
        return "\u001b[$this;${color}m"
    }

    fun colorText(colorEscapeCode: String, text: String?): String {
        return colorEscapeCode + text + AnsiGeneral.reset
    }
}
