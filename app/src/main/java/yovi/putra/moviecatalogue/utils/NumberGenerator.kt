package yovi.putra.moviecatalogue.utils

import java.util.*

object NumberGenerator {
    fun getRandomIntegerWithinRange(min: Int, max: Int): Int {
        val spread = max - min
        return Random().nextInt(spread + 1) + min
    }
}