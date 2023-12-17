package com.vox.exposure.flint

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.vox.exposure.flint_core.Flint.flint
import com.vox.exposure.flint_core.checker.Rule
import com.vox.exposure.flint_core.checker.unaryPlus

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<ViewGroup>(R.id.view_content).flint {
            visibleAreaRatio = 0.7f
            visibilityRules = +Rule.CUSTOM {
                isShown
            }

        }
        addParts()
    }

    private fun addParts() {

    }
}