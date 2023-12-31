package com.vox.exposure.flint

import android.app.Application
import com.vox.exposure.flint_core.Flint

class FlintSampleApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Flint.init(this)
    }

}