package com.vox.exposure.flint_core.model

import android.view.View
import com.vox.exposure.flint_core.GlobalContext.def_visibleAreaRatio
import com.vox.exposure.flint_core.ContextImpl
import com.vox.exposure.flint_core.FlintContext

class FlintView(val view: View) {

    var visibleAreaRatio = def_visibleAreaRatio

    var visibilityRules: Set<Rule>? = null

    var iD = { view.hashCode().toString() }

    internal var context: FlintContext = ContextImpl()

    internal var rootTag = 0
    internal val finalVisibilityRules
        get() = visibilityRules ?: (Rule.BASE + Rule.ALPHA + Rule.WINDOW + Rule.RECT(
            visibleAreaRatio
        ))

    internal var isVisible = false


    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FlintView

        return iD() == other.iD()
    }


}