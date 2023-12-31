package com.vox.exposure.flint_core.structure

import com.vox.exposure.flint_core.GlobalContext

internal class Structure<T> {
    private val operators = hashMapOf<Int, CollectionOperator<T>>()

    fun addElement(rootTag: Int, element: T) {
        val operator = operators.getOrPut(rootTag) {
            if (GlobalContext.collectionType == CollectionType.Linear)
                LinearCollectionOperator()
            else TreeCollectionOperator()
        }
        operator.addElement(element)
    }

    fun removeElement(rootTag: Int, element: T) {
        val operator = operators[rootTag]
            ?: throw IllegalArgumentException(
                "The collection whose rootTag is $rootTag " +
                        "cannot be found when remove"
            )
        operator.removeElement(element)
    }

    fun removeAllElements(rootTag: Int) {
        val operator = operators[rootTag]
            ?: throw IllegalArgumentException(
                "The collection whose rootTag is $rootTag " +
                        "cannot be found when removeAllElements"
            )
        operator.removeAllElements()
    }

    fun getElements(rootTag: Int): Collection<T> {
        val operator = operators[rootTag]
            ?: throw IllegalArgumentException(
                "The collection whose rootTag is $rootTag " +
                        "cannot be found when getElements"
            )
        return operator.getElements()
    }
}