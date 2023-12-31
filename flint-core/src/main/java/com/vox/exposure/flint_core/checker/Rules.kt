package com.vox.exposure.flint_core.checker

import android.view.View
import com.vox.exposure.flint_core.view_ext.baseVisibilityCheck
import com.vox.exposure.flint_core.view_ext.visibilityCheckWithAlpha
import com.vox.exposure.flint_core.view_ext.visibilityCheckWithRect
import com.vox.exposure.flint_core.view_ext.visibilityCheckWithWindow

sealed class Rule {
    data object BASE : Rule()
    data object WINDOW : Rule()
    data object ALPHA : Rule()
    data class RECT(val visiblePercent: Float) : Rule()
    data class CUSTOM(val block: View.() -> Boolean) : Rule()
}


operator fun Rule.plus(other: Rule) = setOf(this, other)
operator fun Rule.unaryPlus() = setOf(this)
operator fun Set<Rule>.plus(other: Rule): Set<Rule> = this + setOf(other)
operator fun Set<Rule>.minus(other: Rule): Set<Rule> = this - setOf(other)


internal fun View.isVisible(rules: Set<Rule>): Boolean {
    return rules.all { rule ->
        when (rule) {
            is Rule.BASE -> baseVisibilityCheck()
            is Rule.WINDOW -> visibilityCheckWithWindow()
            is Rule.ALPHA -> visibilityCheckWithAlpha()
            is Rule.RECT -> visibilityCheckWithRect(rule.visiblePercent)
            is Rule.CUSTOM -> rule.block(this)
        }
    }
}