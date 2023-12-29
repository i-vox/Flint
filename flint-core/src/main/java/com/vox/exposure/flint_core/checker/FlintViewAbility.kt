package com.vox.exposure.flint_core.checker

import android.view.View
import com.vox.exposure.flint_core.Element
import com.vox.exposure.flint_core.GlobalContext
import com.vox.exposure.flint_core.Key
import com.vox.exposure.flint_core.structurer.FlintViewAPIImpl
import kotlinx.coroutines.launch

class FlintViewAbility(internal val view: FlintView)

fun FlintViewAbility.subscribeVisibility(invoker: View.() -> Unit): FlintViewAbility {
    view.context.put(VisibilityListener(invoker))
    return this
}

fun FlintViewAbility.run(): Job {
    val job = Job()
    FlintViewAPIImpl.onCreateFlintView(view.apply { context.put(job) })
    return job
}

class VisibilityListener(val invoker: View.() -> Unit) : Element {
    override val key: Key<VisibilityListener> = ListenerKey

    object ListenerKey : Key<VisibilityListener>
}


class Job : Element {
    override val key: Key<Job> = JobKey

    object JobKey : Key<Job>

    var state = JobState.Running
        internal set


    fun cancel() {
        GlobalContext.scope.launch {
            state = JobState.Canceled
        }
    }


    enum class JobState {
        Running, Canceled, Paused
    }
}




