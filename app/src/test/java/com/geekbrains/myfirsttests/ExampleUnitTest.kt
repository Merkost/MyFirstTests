package com.geekbrains.myfirsttests

import org.junit.Test

import org.junit.Assert.*
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertTimeout
import org.junit.jupiter.api.Assertions.assertTimeoutPreemptively
import org.junit.jupiter.api.DynamicTest.dynamicTest
import java.time.Duration.ofMillis

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `Base assertions`() {
        assertEquals("a", "a")
        assertEquals(2, 1 + 1)
        assertSame(2, 1+1)
        assertNull(null)
    }

    @Test
    fun `Timeout not exceeded`() {
        // Тест упадёт после выполнения лямбда-выражения, если оно превысит 1000 мс
        assertTimeout(ofMillis(1000)) {
            print("Выполняется операция, которая займёт не больше 1 секунды\n")
            Thread.sleep(3)
        }
    }

    @Test
    fun `Timeout not exceeded with preemptively exit`() {
        // Тест упадёт, как только время выполнения превысит 1000 мс
        assertTimeoutPreemptively(ofMillis(1000)) {
            print("Выполняется операция, которая займёт не больше 1 секунды\n")
            Thread.sleep(3)
        }
    }

    private var someVar: Int? = null

    @BeforeEach
    fun `Reset some var`() {
        someVar = 0
    }

    @TestFactory
    fun `Test factory`(): Collection<DynamicTest> {
        val ints = 0..5
        return ints.map {
            dynamicTest("Test №$it incrementing some var") {
                someVar = someVar?.inc()
                print(someVar)
            }
        }.toList()
    }

    @RepeatedTest(5)
    fun `Repeated test with repetition info and test info`(repetitionInfo: RepetitionInfo, testInfo: TestInfo) {
        assertEquals(5, repetitionInfo.totalRepetitions)
        val testDisplayNameRegex = """repetition \d of 5""".toRegex()
        assertTrue(testInfo.displayName.matches(testDisplayNameRegex))
    }


}
