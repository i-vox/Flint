package com.vox.exposure.flint.utils

import android.app.Activity
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import com.vox.exposure.flint.R

fun Activity.addParts(text: String, activityClass: Class<*>) {
    val btn = AppCompatButton(this).apply {
        this.text = text
        this.isAllCaps = false
    }
    findViewById<ViewGroup>(R.id.view_content).addView(btn)
}