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

    const val blackOnBlue = "\u001B[90m;44m"
    const val redOnBlue = "\u001B[91;44m"
    const val greenOnBlue = "\u001B[92;44m"
    const val yellowOnBlue = "\u001B[93;44m"
    const val blueOnBlue = "\u001B[94m;44m"
    const val purpleOnBlue = "\u001B[95m;44m"
    const val cyanOnBlue = "\u001B[96m;44m"
    const val whiteOnBlue = "\u001B[97;44m"

    const val blackOnCyan = "\u001B[90m;46m"
    const val redOnCyan = "\u001B[91;46m"
    const val greenOnCyan = "\u001B[92;46m"
    const val yellowOnCyan = "\u001B[93;46m"
    const val blueOnCyan = "\u001B[94m;46m"
    const val purpleOnCyan = "\u001B[95m;46m"
    const val cyanOnCyan = "\u001B[96m;46m"
    const val whiteOnCyan = "\u001B[97;46m"

    fun colorText(colorEscapeCode: String, text: String?): String {
        return colorEscapeCode + text + AnsiGeneral.reset
    }
}
