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


internal fun View.baseVisibilityCheck(): Boolean {
    if (!isAttachedToWindow) {
        return false
    }

    if (width <= 0 || height <= 0) {
        return false
    }

    if (visibility != View.VISIBLE) {
        return false
    }

    return isShown
}

internal fun View.visibilityCheckWithWindow(): Boolean {
    if (!hasWindowFocus()) {
        return false
    }

    return windowVisibility == View.VISIBLE
}

internal fun View.visibilityCheckWithAlpha(): Boolean = alpha > 0f


internal fun View.visibilityCheckWithRect(visiblePercent: Float): Boolean {
    val viewRect = Rect()
    val visible = getGlobalVisibleRect(viewRect)

    if (!visible) {
        return false
    }
    val w = measuredWidth
    val h = measuredHeight
    val area = w * h

    val visibleArea = viewRect.height() * viewRect.width()

    return if (visiblePercent <= 0f) {
        visibleArea > 0
    } else {
        (visibleArea >= area * visiblePercent)
    }
}

