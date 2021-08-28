package ru.netology.nmedia

import java.math.RoundingMode
import java.text.DecimalFormat

fun convertNumber(number: Int): String? {
    val df = DecimalFormat("#.#")
    df.roundingMode = RoundingMode.DOWN
    when {
        number < 1000 -> return number.toString()
        number in 1000..9999 -> return "${df.format(number.toDouble() / 1000)}K"
        number in 10_000..999_999 -> return "${number / 1000}K"
        number >= 1_000_000 -> return "${df.format(number.toDouble() / 1_000_000)}M"
    }
    return null
}