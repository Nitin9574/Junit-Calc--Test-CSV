package com.qualigy.calculator;

public class Calculator {

    public static Integer add(Integer a, Integer b) {
        return a+b;
    }

    private Integer multiply(Integer a, Integer b) {
        return a*b;
    }

    public int subtract(Integer a, Integer b) {
        return a-b;
    }

    public final int modulus(Integer a, Integer b) {
        return a/b;
    }
}
