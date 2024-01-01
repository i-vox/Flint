package com.vox.exposure.flint_core.check

import android.view.View
import com.vox.exposure.flint_core.model.FlintView
import com.vox.exposure.flint_core.model.Rule
import com.vox.exposure.flint_core.model.VisibilityListener
import com.vox.exposure.flint_core.view_ext.baseVisibilityCheck
import com.vox.exposure.flint_core.view_ext.visibilityCheckWithAlpha
import com.vox.exposure.flint_core.view_ext.visibilityCheckWithRect
import com.vox.exposure.flint_core.view_ext.visibilityCheckWithWindow

internal class VisibilityChecker {
    private fun View.isVisible(rules: Set<Rule>): Boolean {
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

    fun onTraverse(flintView: FlintView) {
        val view = flintView.view
        val currentIsVisible = view.isVisible(flintView.finalVisibilityRules)

        if (flintView.isVisible && !currentIsVisible) {
            toInVisible(flintView)
        } else if (!flintView.isVisible && currentIsVisible) {
            toVisible(flintView)
        }

    }

    fun toInVisible(flintView: FlintView) {
        flintView.context[VisibilityListener.ListenerKey]?.onVisibilityChanged(
            flintView.view,
            VisibilityType.InVisible
        )
    }

    fun toVisible(flintView: FlintView) {
        flintView.context[VisibilityListener.ListenerKey]?.onVisibilityChanged(
            flintView.view,
            VisibilityType.Visible
        )
    }

}