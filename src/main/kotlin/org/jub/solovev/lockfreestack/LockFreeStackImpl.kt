package org.jub.solovev.lockfreestack

import kotlinx.atomicfu.*

class LockFreeStackImpl<T> : BasicStack<T> {

    private val top = atomic<Node<T>?>(null)

    override fun push(element: T) {
        val newNode = Node(element)
        top.loop { topNode ->
            newNode.prev = topNode
            if (top.compareAndSet(topNode, newNode)) return
        }
    }

    override fun pop(): T {
        top.loop { topNode ->
            if (topNode == null) throw NoSuchElementException("pop on empty stack")
            val (element, prevNode) = topNode
            if (top.compareAndSet(topNode, prevNode)) return element
        }
    }

    data class Node<T>(val element: T, var prev: Node<T>? = null)
}