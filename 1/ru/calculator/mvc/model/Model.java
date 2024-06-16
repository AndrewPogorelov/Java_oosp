package ru.calculator.mvc.model;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private String expression;
    private List<String> tokens;
    private int position;

    public Model(String expression) {
        this.expression = expression;
        this.tokens = new ArrayList<>();
        this.position = 0;
        tokenizeExpression();
    }

    public void setExpression(String expression) {
        this.expression = expression;
        position = 0;
        tokens.clear();
        tokenizeExpression();
    }

    private void tokenizeExpression() {
        String[] splitTokens = expression.split(" ");
        for (String token : splitTokens) {
            tokens.add(token);
        }
    }

    public double calculate() {
        double result = evaluateExpression();
        while (position < tokens.size()) {
            String operator = tokens.get(position);
            if (!isOperator(operator)) {
                break;
            }
            position++;
            double operand = evaluateExpression();
            if (operator.equals("+")) {
                result += operand;
            } else if (operator.equals("-")) {
                result -= operand;
            }
        }
        return result;
    }

    private double evaluateExpression() {
        double operand = parseOperand();
        while (position < tokens.size()) {
            String operator = tokens.get(position);
            if (!isOperator(operator)) {
                break;
            }
            position++;
            double secondOperand = parseOperand();
            if (operator.equals("+")) {
                operand += secondOperand;
            } else if (operator.equals("-")) {
                operand -= secondOperand;
            }
        }
        return operand;
    }

    private double parseOperand() {
        String token = tokens.get(position);
        if (token.equals("(")) {
            position++;
            double result = evaluateExpression();
            String closingBracket;
            if (position < tokens.size()) {
                closingBracket = tokens.get(position);
            } else {
                throw new IllegalArgumentException("Error");
            }
            if (closingBracket.equals(")")) {
                position++;
                return result;
            }
            throw new IllegalArgumentException("')' Error");
        } else {
            position++;
            return Double.parseDouble(token);
        }
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("//") || token.equals("^");
    }
}