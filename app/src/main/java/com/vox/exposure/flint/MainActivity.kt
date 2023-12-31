package com.vox.exposure.flint

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.vox.exposure.flint_core.Flint.flint
import com.vox.exposure.flint_core.checker.Rule
import com.vox.exposure.flint_core.checker.run
import com.vox.exposure.flint_core.checker.subscribeVisibility

class MainActivity : AppCompatActivity() {
    private var flag = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<ViewGroup>(R.id.view_content).flint {
            visibleAreaRatio = 0.7f
            visibilityRules = visibilityRules +Rule.CUSTOM {
                isShown && flag
            }
        }.subscribeVisibility {

        }.run()
        addParts()
    }

    private fun addParts() {

    }
}