package com.vox.exposure.flint_core.structurer

import com.vox.exposure.flint_core.R
import com.vox.exposure.flint_core.checker.FlintView
import com.vox.exposure.flint_core.checker.FlintViewAPI
import com.vox.exposure.flint_core.view_ext.addListeners

internal object FlintViewAPIImpl : FlintViewAPI {
    override fun onCreateFlintView(flintView: FlintView) {
        flintView.view.addListeners(
            onAttachInvoker = {
                val rootIndex = rootViewConfig(flintView)

            },
            onDetachInvoker = {

            }
        )
    }

    private fun rootViewConfig(flintView: FlintView): Int {
        flintView.view.rootView.apply {
            if (getTag(R.id.flint_root_view_tag) == null) {
                addListeners(
                    globalLayoutInvoker = {

                    },
                    scrollChangedInvoker = {

                    },
                    windowFocusChangeInvoker = {

                    },
                    onDrawInvoker = {

                    },
                    onAttachInvoker = {

                    },

                    onDetachInvoker = {

                    }
                )
                setTag(R.id.flint_root_view_tag, hashCode())
            }
        }
        return flintView.view.rootView.hashCode()
    }


}