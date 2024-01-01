package com.vox.exposure.flint

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.vox.exposure.flint_core.Flint.flint
import com.vox.exposure.flint_core.model.run
import com.vox.exposure.flint_core.model.subscribeVisibility

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<ViewGroup>(R.id.view_content).flint {
            visibleAreaRatio = 0.7f
        }.subscribeVisibility {

        }.run()
        addParts()
    }

    private fun addParts() {

    }
}