package com.vox.exposure.flint_core.model

import android.os.Looper
import android.view.View
import com.vox.exposure.flint_core.Element
import com.vox.exposure.flint_core.GlobalContext
import com.vox.exposure.flint_core.Key
import com.vox.exposure.flint_core.check.VisibilityChangeListener
import com.vox.exposure.flint_core.check.VisibilityType
import com.vox.exposure.flint_core.structure.StructureManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FlintViewAbility(internal val view: FlintView)

fun FlintViewAbility.subscribeVisibilityChange(invoker: View.(visibilityType: VisibilityType) -> Unit): FlintViewAbility {
    view.context.put(VisibilityListener(invoker))
    return this
}

fun FlintViewAbility.run(): Job {
    val job = Job()
    if (Looper.getMainLooper() == Looper.myLooper()) {
        StructureManager.onCreateFlintView(view.apply { context.put(job) })
    } else {
        GlobalContext.scope.launch(Dispatchers.Main) {
            StructureManager.onCreateFlintView(view.apply { context.put(job) })
        }
    }
    return job
}

internal class VisibilityListener(val invoker: View.(visibilityType: VisibilityType) -> Unit) :
    Element,
    VisibilityChangeListener {
    override val key: Key<VisibilityListener> = ListenerKey

    object ListenerKey : Key<VisibilityListener>

    override fun onVisibilityChanged(view: View, visibilityType: VisibilityType) {
        invoker(view, visibilityType)
    }
}


class Job : Element {
    override val key: Key<Job> = JobKey

    object JobKey : Key<Job>

    var state = JobState.Running
        internal set


    fun cancel() {
        GlobalContext.scope.launch {
            withContext(Dispatchers.Main) {
                state = JobState.Canceled
            }
        }
    }


    enum class JobState {
        Running, Canceled, Paused
    }
}




