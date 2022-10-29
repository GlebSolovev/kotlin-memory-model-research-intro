package org.jub.solovev.lockfreestack

interface BasicStack<T> {

    fun push(element: T)

    fun pop(): T
}