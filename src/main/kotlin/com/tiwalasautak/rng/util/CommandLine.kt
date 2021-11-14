package com.tiwalasautak.rng.util

fun Array<String>.hasSwitch(switch: String): Boolean {
    return this.filter {
        "^--(.*)$".toRegex().find(it)?.let { matchResult ->
            switch.equals(other = matchResult.groupValues[1].trim(), ignoreCase = true)
        } ?: false
    }.any()
}

fun Array<String>.hasParameter(parameter: String): String? {
    return this.firstOrNull {
        "^--(.*)=(.*)$".toRegex().find(it)?.let { matchResult ->
            parameter.equals(other = matchResult.groupValues[1].trim(), ignoreCase = true)
        } ?: false
    }?.let {
        "^--(.*)=(.*)$".toRegex().find(it)?.let { matchResult ->
            matchResult.groupValues[2].trim()
        }
    }
}

fun Array<String>.hasParameters(parameter: String): List<String> {
    return this.filter {
        "^--(.*)=(.*)$".toRegex().find(it)?.let { matchResult ->
            parameter.equals(other = matchResult.groupValues[1].trim(), ignoreCase = true)
        } ?: false
    }.map {
        "^--(.*)=(.*)$".toRegex().find(it)?.let { matchResult ->
            matchResult.groupValues[2].trim()
        } ?: ""
    }
}
