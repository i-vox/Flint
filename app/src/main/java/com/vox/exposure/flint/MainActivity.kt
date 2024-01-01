package com.vox.exposure.flint

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.vox.exposure.flint_core.Flint.flint
import com.vox.exposure.flint_core.model.run
import com.vox.exposure.flint_core.model.subscribeVisibilityChange

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.button).flint {
            visibleAreaRatio = 0.7f
        }.subscribeVisibilityChange {
            Log.d("TAG", "onCreate: $it")
        }.run()
        addParts()
    }

    private fun addParts() {

    }
}