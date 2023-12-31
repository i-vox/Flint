package com.vox.exposure.flint_core

interface FlintContext {
    operator fun <E : Element> get(key: Key<E>): E?
    fun <E : Element> put(element: E): FlintContext
    fun <E : Element> remove(key: Key<E>): FlintContext
    fun getAllElements(): Map<Key<*>, Element>
}

interface Element {
    val key: Key<*>
}

interface Key<E : Element>

open class ContextImpl(protected val elements: MutableMap<Key<*>, Element> = mutableMapOf()) :
    FlintContext {
    override fun <E : Element> get(key: Key<E>): E? {
        return elements[key] as? E
    }

    override fun <E : Element> put(element: E): FlintContext {
        elements[element.key] = element
        return this
    }

    override fun <E : Element> remove(key: Key<E>): FlintContext {
        elements.remove(key)
        return this
    }

    override fun getAllElements(): Map<Key<*>, Element> {
        return elements
    }
}

fun FlintContext.plus(other: FlintContext): FlintContext {
    val newElements = this.getAllElements().toMutableMap()
    other.getAllElements().forEach { (key, element) ->
        newElements[key] = element
    }
    return ContextImpl(newElements)
}