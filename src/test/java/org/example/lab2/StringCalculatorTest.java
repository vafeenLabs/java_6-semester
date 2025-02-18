package org.example.lab2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестовый класс для проверки функциональности класса StringCalculator.
 * Этот класс содержит тесты для различных арифметических операций и выражений.
 */
class StringCalculatorTest {

    private final StringCalculator calculator = new StringCalculator();

    /**
     * Проверяет простую операцию сложения.
     */
    @Test
    void testSimpleAddition() {
        assertEquals(3.0, calculator.evaluate("1 + 2"));
    }

    /**
     * Проверяет простую операцию вычитания.
     */
    @Test
    void testSimpleSubtraction() {
        assertEquals(-1.0, calculator.evaluate("1 - 2"));
    }

    /**
     * Проверяет простую операцию умножения.
     */
    @Test
    void testSimpleMultiplication() {
        assertEquals(6.0, calculator.evaluate("2 * 3"));
    }

    /**
     * Проверяет простую операцию деления.
     */
    @Test
    void testSimpleDivision() {
        assertEquals(2.0, calculator.evaluate("6 / 3"));
    }

    /**
     * Проверяет сложное выражение с несколькими операциями.
     */
    @Test
    void testComplexExpression() {
        assertEquals(7.0, calculator.evaluate("1 + 2 * 3"));
    }

    /**
     * Проверяет выражение с использованием скобок.
     */
    @Test
    void testParenthesesExpression() {
        assertEquals(9.0, calculator.evaluate("(1 + 2) * 3"));
    }

    /**
     * Проверяет вложенные скобки в выражении.
     */
    @Test
    void testNestedParentheses() {
        assertEquals(13.0, calculator.evaluate("2 + (3 + (2 * 4))"));
    }

    /**
     * Проверяет операции с отрицательными числами.
     */
    @Test
    void testNegativeNumbers() {
        assertEquals(-5.0, calculator.evaluate("-2 - 3"));
    }

    /**
     * Проверяет операции с отрицательными числами в скобках.
     */
    @Test
    void testNegativeNumbersWithParentheses() {
        assertEquals(-1.0, calculator.evaluate("(-2) + 1"));
    }

    /**
     * Проверяет смешивание положительных и отрицательных чисел.
     */
    @Test
    void testNegativeAndPositiveNumbers() {
        assertEquals(0.0, calculator.evaluate("-1 + 1"));
    }

    /**
     * Проверяет операции с числами с плавающей точкой.
     */
    @Test
    void testFloatingPointNumbers() {
        assertEquals(2.5, calculator.evaluate("1.5 + 1"));
    }

    /**
     * Проверяет умножение чисел с плавающей точкой.
     */
    @Test
    void testFloatingPointMultiplication() {
        assertEquals(4.5, calculator.evaluate("1.5 * 3"));
    }

    /**
     * Проверяет выброс исключения при делении на ноль.
     */
    @Test
    void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.evaluate("1 / 0"));
    }

    /**
     * Проверяет выброс исключения при некорректном выражении (например, отсутствует второй операнд).
     */
    @Test
    void testInvalidExpression() {
        assertThrows(IllegalArgumentException.class, () -> calculator.evaluate("1 +"));
    }

    /**
     * Проверяет выброс исключения при использовании недопустимого символа в выражении.
     */
    @Test
    void testInvalidSymbol() {
        assertThrows(IllegalArgumentException.class, () -> calculator.evaluate("1 $ 2"));
    }

    /**
     * Проверяет корректную обработку пробелов в выражении.
     */
    @Test
    void testMultipleSpaces() {
        assertEquals(3.0, calculator.evaluate("   1   +   2   "));
    }

    /**
     * Проверяет сложное выражение с отрицательными числами и скобками.
     */
    @Test
    void testComplexExpressionWithNegativeNumbersAndParentheses() {
        assertEquals(-1.0, calculator.evaluate("(-1 + 2) * (-1)"));
    }

    /**
     * Проверяет работу калькулятора при начале выражения с отрицательного знака.
     */
    @Test
    void testStartingWithNegativeSign() {
        assertEquals(-2.0, calculator.evaluate("-2"));
    }
}
