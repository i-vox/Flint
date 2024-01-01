package com.vox.exposure.flint_core.check

import android.view.View

interface VisibilityChangeListener {
    fun onVisibilityChanged(view: View, visibilityType: VisibilityType)
}

enum class VisibilityType {
    Visible, InVisible
}