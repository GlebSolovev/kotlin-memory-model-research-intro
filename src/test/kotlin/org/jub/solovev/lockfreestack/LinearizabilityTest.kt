@file:Suppress("unused")

package org.jub.solovev.lockfreestack

import org.jetbrains.kotlinx.lincheck.LinChecker
import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.annotations.Param
import org.jetbrains.kotlinx.lincheck.paramgen.IntGen
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressCTest
import org.jetbrains.kotlinx.lincheck.verifier.VerifierState
import kotlin.test.Test

@StressCTest(minimizeFailedScenario = false)
@Param(name = "element", gen = IntGen::class, conf = "1:5")
internal class LinearizabilityTest : VerifierState() {

    private val stack: BasicStack<Int> = LockFreeStackImpl()

    @Operation(params = ["element"])
    fun push(element: Int) = stack.push(element)

    @Operation(handleExceptionsAsResult = [NoSuchElementException::class])
    fun pop(): Int = stack.pop()

    @Test
    fun `test linearizability`() = LinChecker.check(LinearizabilityTest::class.java)

    override fun extractState(): Any = stack
}