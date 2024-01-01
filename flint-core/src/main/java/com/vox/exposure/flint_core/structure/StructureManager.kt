package com.vox.exposure.flint_core.structure

import com.vox.exposure.flint_core.R
import com.vox.exposure.flint_core.check.VisibilityChecker
import com.vox.exposure.flint_core.detect.Detector
import com.vox.exposure.flint_core.model.FlintView
import com.vox.exposure.flint_core.model.FlintViewInterface
import com.vox.exposure.flint_core.view_ext.addListeners

internal object StructureManager : FlintViewInterface {

    private val viewStructure by lazy {
        Structure<FlintView>()
    }

    private val visibilityChecker by lazy {
        VisibilityChecker()
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

    fun traverse(rootTag: Int) {
        viewStructure.getElements(rootTag)?.forEach { flintView ->
            visibilityChecker.onTraverse(flintView)
        }
    }


    private fun onAttach(flintView: FlintView) {
        val rootTag = rootViewConfig(flintView)
        viewStructure.addElement(rootTag, flintView)
    }

    private fun rootViewConfig(flintView: FlintView): Int {
        flintView.view.rootView.apply {
            if (getTag(R.id.flint_root_view_tag) == null) {
                setTag(R.id.flint_root_view_tag, hashCode())
                addListeners(
                    globalLayoutInvoker = {
                        Detector.triggerDetect(hashCode())
                    },
                    scrollChangedInvoker = {
                        Detector.triggerDetect(hashCode())
                    },
                    windowFocusChangeInvoker = {
                        Detector.triggerDetect(hashCode())
                    },
                    onDrawInvoker = {
                        Detector.triggerDetect(hashCode())
                    },
                    onAttachInvoker = {

                    },

                    onDetachInvoker = {

                    },
                    isRootView = true
                )
            }
        }
        return flintView.view.rootView.hashCode().apply {
            flintView.rootTag = this
        }
    }

    private fun onDetach(flintView: FlintView) {
        flintView.isVisible = false
        viewStructure.removeElement(flintView.rootTag, flintView)
    }


}