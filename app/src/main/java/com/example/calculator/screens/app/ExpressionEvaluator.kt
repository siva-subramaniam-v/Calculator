package com.example.calculator.screens.app

import java.util.regex.Pattern

class ExpressionEvaluator {
    fun evaluate(expression: String): String {
        var expression = expression

        if (expression == "") return ""

        if (expression.contains("(")) {
            val extractParentheses =
                Pattern.compile("\\((((\\-?)((\\d+\\.\\d+)|(\\d+)))[\\+\\-\\*\\/])+((\\-?)((\\d+\\.\\d+)|(\\d+)))\\)")
            var subExpression: String
            var subExpResult: String
            while (expression.contains("(")) {
                val matcher = extractParentheses.matcher(expression)
                if (matcher.find()) {
                    subExpression = matcher.group()
                    subExpResult = evaluateExp(subExpression.substring(1, subExpression.length - 1))
                    expression = expression.substring(0, matcher.start()) +
                            subExpResult +
                            expression.substring(matcher.end(), expression.length)
                }
            }
        }
        return evaluateExp(expression)
    }

    /*public boolean validate (String expression) {
        if (! Character.isDigit(expression.charAt(0)) && expression.charAt(0) != '-')
            return false;

        if (! ! Character.isDigit(expression.charAt(expression.length()-1)))
            return false;

        for (int index = 1; index < expression.length(); index++) {
            if (Character.isDigit(expression.charAt(index))) {
                if ()
            }
        }

        return true;
    }*/
    private fun evaluateExp(expression: String): String {
        var expression = expression
        while (expression.contains("/")) {
            expression = performOperation(expression, '/')
        }
        while (expression.contains("*")) {
            expression = performOperation(expression, '*')
        }
        while (expression.contains("-")) {
            if (expression[0] == '-') {
                if (!expression.substring(1, expression.length).contains("-")) break
            }
            expression = performOperation(expression, '-')
        }
        while (expression.contains("+")) {
            expression = performOperation(expression, '+')
        }
        return expression
    }

    private fun performOperation(expression: String, operation: Char): String {
        val operatorIndex: Int
        var startIndex: Int
        var endIndex: Int
        val number1: Double
        val number2: Double
        var result = 0.0
        operatorIndex = expression.indexOf(operation)
        startIndex = operatorIndex - 1
        endIndex = operatorIndex + 1
        while (startIndex > 0) {
            if (!Character.isDigit(expression[startIndex - 1]) && expression[startIndex - 1] != '.') {
                if (startIndex == 1 && expression[startIndex - 1] == '-') {
                    startIndex -= 1
                    continue
                }
                break
            }
            startIndex -= 1
        }
        while (endIndex < expression.length) {
            if (!Character.isDigit(expression[endIndex]) && expression[endIndex] != '.') {
                if (endIndex == operatorIndex + 1 && expression[endIndex] == '-') {
                    endIndex += 1
                    continue
                }
                break
            }
            endIndex += 1
        }
        number1 = expression.substring(startIndex, operatorIndex).toDouble()
        number2 = expression.substring(operatorIndex + 1, endIndex).toDouble()
        when (operation) {
            '/' -> result = number1 / number2
            '*' -> result = number1 * number2
            '+' -> result = number1 + number2
            '-' -> result = number1 - number2
        }
        return expression.substring(0, startIndex) +
                result +
                expression.substring(endIndex, expression.length)
    }
}