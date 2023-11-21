/*
 * Copyright 2023 i-vox
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
