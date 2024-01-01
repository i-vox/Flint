package com.vox.exposure.flint_core.structure

import com.vox.exposure.flint_core.GlobalContext

internal class Structure<T> {
    private val operators = hashMapOf<Int, CollectionOperator<T>>()

    fun addElement(rootTag: Int, element: T) {
        val operator = operators.getOrPut(rootTag) {
            if (GlobalContext.collectionType == CollectionType.Linear)
                ViewLinearCollection()
            else ViewTreeCollection()
        }
        operator.addElement(element)
    }

    fun removeElement(rootTag: Int, element: T) {
        operators[rootTag]?.removeElement(element)
    }

    fun removeAllElements(rootTag: Int) {
        operators[rootTag]?.removeAllElements()
    }

    fun getElements(rootTag: Int): Collection<T>? {
        return operators[rootTag]?.getElements()
    }
}