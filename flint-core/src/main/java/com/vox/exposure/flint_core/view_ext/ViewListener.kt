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

    val layoutListener = if (globalLayoutInvoker != null) {
        ViewTreeObserver.OnGlobalLayoutListener {
            globalLayoutInvoker()
        }
    } else {
        null
    }

    val scrollListener = if (scrollChangedInvoker != null) {
        ViewTreeObserver.OnScrollChangedListener {
            scrollChangedInvoker()
        }
    } else {
        null
    }

    val focusChangeListener = if (windowFocusChangeInvoker != null) {
        ViewTreeObserver.OnWindowFocusChangeListener { hasFocus ->
            windowFocusChangeInvoker(hasFocus)
        }
    } else {
        null
    }

    val onDrawListener = if (onDrawInvoker != null) {
        ViewTreeObserver.OnDrawListener {
            onDrawInvoker()
        }
    } else {
        null
    }

    addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
        override fun onViewAttachedToWindow(v: View) {
            v.post {
                v.viewTreeObserver.apply {
                    if (layoutListener != null) {
                        addOnGlobalLayoutListener(layoutListener)
                    }

                    if (scrollListener != null) {
                        addOnScrollChangedListener(scrollListener)
                    }

                    if (focusChangeListener != null) {
                        addOnWindowFocusChangeListener(focusChangeListener)
                    }

                    if (onDrawListener != null) {
                        addOnDrawListener(onDrawListener)
                    }
                }
            }
            if (onAttachInvoker != null) {
                onAttachInvoker()
            }
        }

        override fun onViewDetachedFromWindow(v: View) {
            v.post {
                v.viewTreeObserver.apply {
                    if (layoutListener != null) {
                        removeOnGlobalLayoutListener(layoutListener)
                    }

                    if (focusChangeListener != null) {
                        removeOnWindowFocusChangeListener(focusChangeListener)
                    }

                    if (scrollListener != null) {
                        removeOnScrollChangedListener(scrollListener)
                    }

                    if (onDrawListener != null) {
                        removeOnDrawListener(onDrawListener)
                    }
                }
            }

            if (onDetachInvoker != null) {
                onDetachInvoker()
            }
        }

    })
}