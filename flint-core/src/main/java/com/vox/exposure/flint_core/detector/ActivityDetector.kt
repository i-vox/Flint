package com.vox.exposure.flint_core.detector

import android.app.Activity
import android.app.Application
import android.os.Bundle

/**
 * Perceive the life cycle of all activities
 * As part of the exposure detection trigger
 * Provide opportunities to clear exposed business
 */
internal object ActivityDetector : Application.ActivityLifecycleCallbacks {
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

    }

    override fun onActivityStarted(activity: Activity) {

    }

    override fun onActivityResumed(activity: Activity) {

    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStopped(activity: Activity) {

    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityDestroyed(activity: Activity) {

    }
}