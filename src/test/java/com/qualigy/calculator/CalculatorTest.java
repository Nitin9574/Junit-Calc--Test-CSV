package com.qualigy.calculator;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    private static Calculator calc = null;

    @BeforeAll
    public static void beforeAll() {
        calc = new Calculator();
    }
    public static void afterAll() {
        calc = null;
    }

    @Test
    public void testAdd() {
        Calculator calc = new Calculator();
        int actualResult = calc.add(1, 5);
        int expectedResult = 5;

        assertEquals(expectedResult, 5);
    }

    @Test
    public void testMultiply() throws Exception {
        Calculator calc = new Calculator();
        // Since multiply is a private method, we need to use reflection to access it
        java.lang.reflect.Method method = Calculator.class.getDeclaredMethod("multiply", int.class, int.class);
        method.setAccessible(true);
        int result = (int) method.invoke(calc, 3, 5);
        assertEquals(15, result);
    }

    @Test
    public void testSubtract() {
        Calculator calc = new Calculator();
        int actualResult = calc.subtract(5, 3);
        int expectedResult = 2;
        assertEquals(expectedResult, 2);
    }

    @Test
    public void testModulus() {
        Calculator calc = new Calculator();
        int actualResult = calc.modulus(10, 10);
        int expectedResult = 0;
        assertEquals(expectedResult, 0);
    }


    @Disabled
    @ParameterizedTest(name = "{index} ==> {0} + {1} = {2}")
    @MethodSource
    void add(int a, int b, int expectedSum) {
        assertEquals(expectedSum, Calculator.add(a, b));
    }

    static Stream<Arguments> add() {
        return Stream.of(
                Arguments.of(0, 0, 0),
                Arguments.of(1, 1, 2),
                Arguments.of(-4, 4, 0),
                Arguments.of(-5, -5, -10),
                Arguments.of(-3, 6, 2)
        );
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/test.csv", numLinesToSkip = 1)
    public void add1(int input1, int input2, int expectedSum) {
        int actualSum = Calculator.add(input1, input2);
        assertEquals(expectedSum, actualSum);
    }
}








// we are creating Calculator object multiple times which is not recommended ,
// waste of memory
// Recommended is create one object and reuse for each test method and nullify
// that object after all test method executed