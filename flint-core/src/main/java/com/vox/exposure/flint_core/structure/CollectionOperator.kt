package com.vox.exposure.flint_core.structure

internal interface CollectionOperator<T> {
    fun addElement(element: T)
    fun removeElement(element: T)
    fun removeAllElements()
    fun getElements(): Collection<T>
}

enum class CollectionType {
    Linear, Tree
}
