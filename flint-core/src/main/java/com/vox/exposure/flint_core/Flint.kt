/*
 * Copyright 2023 i-vox
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vox.exposure.flint_core

import android.app.Application
import android.view.View
import com.vox.exposure.flint_core.checker.FlintView
import com.vox.exposure.flint_core.checker.FlintViewAbility
import com.vox.exposure.flint_core.detector.ActivityDetector
import com.vox.exposure.flint_core.structure.CollectionType
import kotlinx.coroutines.CoroutineScope

/**
 * flint's global configuration entry
 */
object Flint {

    /**
     * Initialize the dependencies required to access the Flint SDK
     */
    fun init(
        application: Application,
        scope: CoroutineScope? = null,
        collectionType: CollectionType = CollectionType.Linear
    ) {
        application.registerActivityLifecycleCallbacks(ActivityDetector)

        if (scope != null) {
            GlobalContext.scope = scope
        }

        GlobalContext.collectionType = collectionType
    }

    fun View.flint(invoker: FlintView.() -> Unit): FlintViewAbility {
        val flintView = FlintView(this).apply(invoker)
        return FlintViewAbility(flintView)
    }

}