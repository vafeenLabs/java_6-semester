package org.example.lab2;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;
import java.util.*;

/**
 * Класс StringCalculator предназначен для вычисления арифметических выражений,
 * представленных в инфиксной нотации. Он поддерживает операции сложения, вычитания,
 * умножения и деления, а также работу со скобками.
 */
public class StringCalculator {

    /**
     * Вычисляет значение арифметического выражения.
     *
     * @param expression строка, представляющая арифметическое выражение.
     * @return результат вычисления выражения.
     * @throws IllegalArgumentException если выражение содержит недопустимые символы или имеет неверный формат.
     * @throws ArithmeticException если происходит деление на ноль.
     */
    public double evaluate(String expression) throws IllegalArgumentException, ArithmeticException {
        expression = expression.replaceAll("\\s", "");

        if (expression.isEmpty()) {
            throw new IllegalArgumentException("Expression is empty");
        }

        // Преобразуем инфиксное выражение в постфиксную нотацию
        List<String> tokens = infixToPostfix(expression);

        Stack<Double> operands = new Stack<>();

        for (String token : tokens) {
            if (isNumeric(token)) {
                operands.push(Double.parseDouble(token));
            } else {
                if (operands.size() < 2) {
                    throw new IllegalArgumentException("Invalid expression: not enough operands for operator " + token);
                }
                double operand2 = operands.pop();
                double operand1 = operands.pop();
                operands.push(performOperation(token.charAt(0), operand1, operand2));
            }
        }

        if (operands.size() != 1) {
            throw new IllegalArgumentException("Invalid expression: unbalanced operators and operands");
        }

        return operands.pop();
    }

    /**
     * Преобразует инфиксное выражение в постфиксную нотацию (обратная польская нотация).
     *
     * @param infixExpression строка, представляющая инфиксное выражение.
     * @return список строк, представляющий постфиксное выражение.
     * @throws IllegalArgumentException если выражение имеет неверный формат.
     */
    private List<String> infixToPostfix(String infixExpression) {
        List<String> postfixList = new ArrayList<>();
        Stack<Character> stack = new Stack<>();
        boolean expectingOperand = true;

        try {
            for (int i = 0; i < infixExpression.length(); i++) {
                char ch = infixExpression.charAt(i);
                if (Character.isDigit(ch) || (ch == '-' && expectingOperand && (i == 0 || !Character.isDigit(infixExpression.charAt(i - 1))))) {
                    StringBuilder operand = new StringBuilder();
                    operand.append(ch);
                    while (i + 1 < infixExpression.length() && (Character.isDigit(infixExpression.charAt(i + 1)) || infixExpression.charAt(i + 1) == '.')) {
                        operand.append(infixExpression.charAt(++i));
                    }
                    postfixList.add(operand.toString());
                    expectingOperand = false;
                } else if (ch == '(') {
                    stack.push(ch);
                    expectingOperand = true;
                } else if (ch == ')') {
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        postfixList.add(String.valueOf(stack.pop()));
                    }
                    if (stack.isEmpty()) {
                        throw new IllegalArgumentException("Mismatched parentheses");
                    }
                    stack.pop(); // Удаляем '('
                    expectingOperand = false;
                } else if (isOperator(ch)) {
                    while (!stack.isEmpty() && precedence(ch) <= precedence(stack.peek())) {
                        postfixList.add(String.valueOf(stack.pop()));
                    }
                    stack.push(ch);
                    expectingOperand = true;
                } else {
                    throw new IllegalArgumentException("Unknown symbol in expression: " + ch);
                }
            }

            while (!stack.isEmpty()) {
                if (stack.peek() == '(') {
                    throw new IllegalArgumentException("Mismatched parentheses");
                }
                postfixList.add(String.valueOf(stack.pop()));
            }
        } catch (EmptyStackException e) {
            throw new IllegalArgumentException("Invalid expression format");
        }

        return postfixList;
    }

    /**
     * Возвращает приоритет оператора.
     *
     * @param operator оператор, для которого нужно получить приоритет.
     * @return целочисленный приоритет оператора.
     */
    private int precedence(char operator) {
        return switch (operator) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            default -> -1;
        };
    }

    /**
     * Проверяет, является ли строка числом.
     *
     * @param str строка для проверки.
     * @return true, если строка является числом; false в противном случае.
     */
    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Проверяет, является ли символ оператором.
     *
     * @param operator символ для проверки.
     * @return true, если символ является оператором; false в противном случае.
     */
    private boolean isOperator(Character operator) {
        return operator == '+' || operator == '-' || operator == '*' || operator == '/';
    }

    /**
     * Выполняет арифметическую операцию над двумя операндами.
     *
     * @param operator оператор для выполнения операции.
     * @param a первый операнд.
     * @param b второй операнд.
     * @return результат операции.
     * @throws ArithmeticException если происходит деление на ноль.
     * @throws IllegalArgumentException если оператор не распознан.
     */
    private double performOperation(char operator, double a, double b) {
        return switch (operator) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> {
                if (b == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                yield a / b;
            }
            default -> throw new IllegalArgumentException("Unknown operator: " + operator);
        };
    }
}