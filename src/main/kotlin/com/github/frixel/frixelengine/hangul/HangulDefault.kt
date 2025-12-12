package com.github.frixel.frixelengine.hangul

import org.jspecify.annotations.NullMarked
import java.util.*

@NullMarked
enum class HangulDefault {
    옵션,

    ;
    override fun toString(): String {
        return name.lowercase(Locale.getDefault())
    }
}
