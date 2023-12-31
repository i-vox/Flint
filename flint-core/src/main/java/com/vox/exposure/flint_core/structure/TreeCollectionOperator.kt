package com.vox.exposure.flint_core.structure

class TreeCollectionOperator<T> : CollectionOperator<T> {

    //todo Provide complete implementation

    private val elements = sortedSetOf<T>()

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
