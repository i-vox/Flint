package com.vox.exposure.flint_core.structure

class LinearCollectionOperator<T> : CollectionOperator<T> {
    private val elements = mutableSetOf<T>()

    override fun addElement(element: T) {
        elements.add(element)
    }

    override fun removeElement(element: T) {
        elements.remove(element)
    }

    override fun removeAllElements() {
        elements.clear()
    }

    override fun getElements(): Collection<T> {
        return elements
    }
}
