package org.example.lab2;

import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    private final StringCalculator calculator = new StringCalculator();

    @Test
    void testSimpleAddition() {
        assertEquals(3.0, calculator.evaluate("1 + 2"));
    }

    @Test
    void testSimpleSubtraction() {
        assertEquals(-1.0, calculator.evaluate("1 - 2"));
    }

    @Test
    void testSimpleMultiplication() {
        assertEquals(6.0, calculator.evaluate("2 * 3"));
    }

    @Test
    void testSimpleDivision() {
        assertEquals(2.0, calculator.evaluate("6 / 3"));
    }

    @Test
    void testComplexExpression() {
        assertEquals(7.0, calculator.evaluate("1 + 2 * 3"));
    }

    @Test
    void testParenthesesExpression() {
        assertEquals(9.0, calculator.evaluate("(1 + 2) * 3"));
    }

    @Test
    void testNestedParentheses() {
        assertEquals(13.0, calculator.evaluate("2 + (3 + (2 * 4))"));
    }

    @Test
    void testNegativeNumbers() {
        assertEquals(-5.0, calculator.evaluate("-2 - 3"));
    }

    @Test
    void testNegativeNumbersWithParentheses() {
        assertEquals(-1.0, calculator.evaluate("(-2) + 1"));
    }

    @Test
    void testNegativeAndPositiveNumbers() {
        assertEquals(0.0, calculator.evaluate("-1 + 1"));
    }

    @Test
    void testFloatingPointNumbers() {
        assertEquals(2.5, calculator.evaluate("1.5 + 1"));
    }

    @Test
    void testFloatingPointMultiplication() {
        assertEquals(4.5, calculator.evaluate("1.5 * 3"));
    }

    @Test
    void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.evaluate("1 / 0"));
    }

    @Test
    void testInvalidExpression() {
        assertThrows(EmptyStackException.class, () -> calculator.evaluate("1 +"));
    }

    @Test
    void testInvalidSymbol() {
        assertThrows(IllegalArgumentException.class, () -> calculator.evaluate("1 $ 2"));
    }

    @Test
    void testMultipleSpaces() {
        assertEquals(3.0, calculator.evaluate("   1   +   2   "));
    }

    @Test
    void testComplexExpressionWithNegativeNumbersAndParentheses() {
        assertEquals(-1.0, calculator.evaluate("(-1 + 2) * (-1)"));
    }

    @Test
    void testStartingWithNegativeSign() {
        assertEquals(-2.0, calculator.evaluate("-2"));
    }
}
