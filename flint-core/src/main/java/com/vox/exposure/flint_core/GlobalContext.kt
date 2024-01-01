package com.vox.exposure.flint_core

import com.vox.exposure.flint_core.structure.CollectionType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

internal object GlobalContext : ContextImpl() {
    const val def_visibleAreaRatio = 0.5f
    const val def_detectionInterval = 100L
    const val def_exposureDuration = 500

    var scope: CoroutineScope = CoroutineScope(
        SupervisorJob()
                + Dispatchers.Default
    )

    var collectionType = CollectionType.Linear

    var detectionInterval = def_detectionInterval

}