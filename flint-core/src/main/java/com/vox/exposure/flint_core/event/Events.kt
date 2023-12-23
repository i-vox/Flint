package com.vox.exposure.flint_core.event

import com.vox.exposure.flint_core.checker.FlintView
import com.vox.exposure.flint_core.view_ext.addListeners


internal fun onCreateFlintView(flintView: FlintView) {

    flintView.view.addListeners(
        onAttachInvoker = {
            flintView.view.rootView.apply {
                if (tag == null) {
                    addListeners(
                        globalLayoutInvoker = {

                        },
                        scrollChangedInvoker = {

                        },

                        )
                }
            }

        },
        onDetachInvoker = {

        }
    )


}