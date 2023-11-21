package com.vox.exposure.flint_core.view_ext

import android.view.View
import android.view.ViewTreeObserver

/**
 * Configure listeners uniformly for views
 * Custom logic can be configured
 * Automatically register/unregister based on attach/detach status
 */
internal fun View.addListeners(
    globalLayoutInvoker: (() -> Unit)? = null,
    scrollChangedInvoker: (() -> Unit)? = null,
    windowFocusChangeInvoker: ((hasFocus: Boolean) -> Unit)? = null,
    onDrawInvoker: (() -> Unit)? = null,
    onAttachInvoker: (() -> Unit)? = null,
    onDetachInvoker: (() -> Unit)? = null
) {


    val layoutListener = ViewTreeObserver.OnGlobalLayoutListener {
        if (globalLayoutInvoker != null) {
            globalLayoutInvoker()
        }
    }


    val scrollListener: ViewTreeObserver.OnScrollChangedListener =
        ViewTreeObserver.OnScrollChangedListener {
            if (scrollChangedInvoker != null) {
                scrollChangedInvoker()
            }
        }


    val focusChangeListener = ViewTreeObserver.OnWindowFocusChangeListener { hasFocus ->
        if (windowFocusChangeInvoker != null) {
            windowFocusChangeInvoker(hasFocus)
        }
    }


    val onDrawListener = ViewTreeObserver.OnDrawListener {
        if (onDrawInvoker != null) {
            onDrawInvoker()
        }
    }

    addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
        override fun onViewAttachedToWindow(v: View) {
            v.post {
                v.viewTreeObserver.apply {
                    addOnGlobalLayoutListener(layoutListener)
                    addOnScrollChangedListener(scrollListener)
                    addOnWindowFocusChangeListener(focusChangeListener)
                    addOnDrawListener(onDrawListener)
                }
            }
            if (onAttachInvoker != null) {
                onAttachInvoker()
            }
        }

        override fun onViewDetachedFromWindow(v: View) {
            v.post {
                v.viewTreeObserver.apply {
                    removeOnGlobalLayoutListener(layoutListener)
                    removeOnWindowFocusChangeListener(focusChangeListener)
                    removeOnScrollChangedListener(scrollListener)
                    removeOnDrawListener(onDrawListener)
                }
            }

            if (onDetachInvoker != null) {
                onDetachInvoker()
            }
            // removeOnAttachStateChangeListener(this)
        }

    })
}