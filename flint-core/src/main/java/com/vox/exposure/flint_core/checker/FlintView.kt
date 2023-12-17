package com.vox.exposure.flint_core.checker

import android.view.View
import com.vox.exposure.flint_core.GlobalConfig.def_visibleAreaRatio

open class FlintView(val view: View) {

    var visibleAreaRatio = def_visibleAreaRatio

    var visibilityRules = Rule.BASE + Rule.ALPHA + Rule.WINDOW + Rule.RECT(visibleAreaRatio)


}