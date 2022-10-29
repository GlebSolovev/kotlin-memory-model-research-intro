package org.jub.solovev.lockfreestack

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.random.Random
import kotlin.test.assertEquals

internal class BasicStackTest {

    private lateinit var stack: BasicStack<Int>

    @BeforeEach
    fun initStack() {
        stack = LockFreeStackImpl()
    }

    @Test
    fun `test push pop`() {
        stack.push(1)
        assertEquals(1, stack.pop())
    }

    @Test
    fun `test pop on empty stack throws`() {
        assertThrows<NoSuchElementException> { stack.pop() }
    }

    @Test
    fun `test stress`() {
        val javaStack = java.util.Stack<Int>()
        val steps = 100000
        repeat(steps) {
            val randomElement = Random.nextInt()
            // do more push-es than pop-s
            when {
                randomElement % 3 == 0 -> {
                    if (javaStack.isNotEmpty()) {
                        assertEquals(javaStack.pop(), stack.pop())
                    } else {
                        assertThrows<NoSuchElementException> { stack.pop() }
                    }
                }

                else -> {
                    stack.push(randomElement)
                    javaStack.push(randomElement)
                }
            }
        }
    }
}