package com.vox.exposure.flint_core.view_ext

import android.graphics.Rect
import android.view.View

/**
 * flint’s built-in standard view visibility judgment strategy
 * Note that the occlusion of views at the same level is not considered.
 */
internal fun View.normalVisibilityCheck(visiblePercent: Float): Boolean {
    if (!baseVisibilityCheck(this)) {
        return false
    }

    val viewRect = Rect()
    val visible = getGlobalVisibleRect(viewRect)

    if (!visible) {
        return false
    }
    val w = measuredWidth
    val h = measuredHeight
    val area = w * h

    val visibleArea = viewRect.height() * viewRect.width()

    return if (visiblePercent == 0f) {
        visibleArea > 0
    } else {
        (visibleArea >= area * visiblePercent)
    }
}


private fun baseVisibilityCheck(view: View): Boolean {
    if (!view.isAttachedToWindow) {
        return false
    }

    if (!view.hasWindowFocus()) {
        return false
    }

    if (view.visibility != View.VISIBLE) {
        return false
    }

    if (view.windowVisibility != View.VISIBLE) {
        return false
    }

    if (!view.isShown) {
        return false
    }

    if (view.width <= 0 || view.height <= 0 || view.alpha <= 0.0f) {
        return false
    }
    return true
}
