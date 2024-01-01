package com.vox.exposure.flint_core.detect

import com.vox.exposure.flint_core.GlobalContext
import com.vox.exposure.flint_core.structure.StructureManager

object Detector {

    private var lastDetectionTime = 0L

    fun triggerDetect(
        rootTag: Int,
        isDiscrete: Boolean = true
    ) {
        val currentTime = System.currentTimeMillis()
        if (isDiscrete
            && (currentTime - lastDetectionTime < GlobalContext.detectionInterval)
        ) {
            return
        }
        lastDetectionTime = currentTime

        StructureManager.traverse(rootTag)
    }

}