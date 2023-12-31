package com.vox.exposure.flint_core.structure

import com.vox.exposure.flint_core.R
import com.vox.exposure.flint_core.checker.FlintView
import com.vox.exposure.flint_core.checker.FlintViewAPI
import com.vox.exposure.flint_core.view_ext.addListeners

internal object StructureManager : FlintViewAPI {

    private val viewStructure by lazy {
        Structure<FlintView>()
    }

    override fun onCreateFlintView(flintView: FlintView) {
        if (flintView.view.isAttachedToWindow) {
            onAttach(flintView)
        }

        flintView.view.addListeners(
            onAttachInvoker = {
                onAttach(flintView)

            },
            onDetachInvoker = {
                onDetach(flintView)
            }
        )
    }


    private fun onAttach(flintView: FlintView) {
        val rootTag = rootViewConfig(flintView)
        viewStructure.addElement(rootTag, flintView)
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
        return flintView.view.rootView.hashCode().apply {
            flintView.rootTag = this
        }
    }

    private fun onDetach(flintView: FlintView) {
        viewStructure.removeElement(flintView.rootTag, flintView)
    }


}