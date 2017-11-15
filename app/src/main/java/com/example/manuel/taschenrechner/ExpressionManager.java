package com.example.manuel.taschenrechner;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

/**
 * Created by manuel on 14.11.17.
 */

public class ExpressionManager {
    private static ExpressionManager em;

    private StringBuilder expression;
    private StringBuilder result;
    private boolean comma;
    private boolean operator;
    private int brackets;

    private ExpressionManager() {
        expression = new StringBuilder();
        result = new StringBuilder();
        comma = false;
        operator = true;
        brackets = 0;
    }

    public static ExpressionManager getInstance() {
        if (em == null) {
            em = new ExpressionManager();
        }
        return em;
    }

    public void zero() {
        if (result.length() != 0) deleteAll();
        expression.append("0");
        operator = false;
    }

    public void one() {
        if (result.length() != 0) deleteAll();
        expression.append("1");
        operator = false;
    }

    public void two() {
        if (result.length() != 0) deleteAll();
        expression.append("2");
        operator = false;
    }

    public void three() {
        if (result.length() != 0) deleteAll();
        expression.append("3");
        operator = false;
    }

    public void four() {
        if (result.length() != 0) deleteAll();
        expression.append("4");
        operator = false;
    }

    public void five() {
        if (result.length() != 0) deleteAll();
        expression.append("5");
        operator = false;
    }

    public void six() {
        if (result.length() != 0) deleteAll();
        expression.append("6");
        operator = false;
    }

    public void seven() {
        if (result.length() != 0) deleteAll();
        expression.append("7");
        operator = false;
    }

    public void eight() {
        if (result.length() != 0) deleteAll();
        expression.append("8");
        operator = false;
    }

    public void nine() {
        if (result.length() != 0) deleteAll();
        expression.append("9");
        operator = false;
    }

    public void comma() {
        if (result.length() != 0) return;
        if (expression.length() > 0 && !comma) {
            if (result.length() != 0) result.setLength(0);
            expression.append(",");
        }
        comma = true;
    }

    public void plus() {
        if (isLastBracket()) return;
        if (operator == false) {
            if (isLastComma()) expression.deleteCharAt(expression.length() - 1);
            if (result.length() != 0) {
                expression.append(result);
                result.setLength(0);
            }
            expression.append("+");
            operator = true;
            comma = false;
        }
    }

    public void minus() {
        if (operator == false || expression.length() == 0) {
            if (isLastComma()) expression.deleteCharAt(expression.length() - 1);
            if (result.length() != 0) {
                expression.append(result);
                result.setLength(0);
            }
            expression.append("-");
            operator = true;
            comma = false;
        }
    }

    public void multiply() {
        if (isLastBracket()) return;
        if (operator == false) {
            if (isLastComma()) expression.deleteCharAt(expression.length() - 1);
            if (result.length() != 0) {
                expression.append(result);
                result.setLength(0);
            }
            expression.append("*");
            operator = true;
            comma = false;
        }
    }

    public void divide() {
        if (isLastBracket()) return;
        if (operator == false) {
            if (isLastComma()) expression.deleteCharAt(expression.length() - 1);
            if (result.length() != 0) {
                expression.append(result);
                result.setLength(0);
            }
            expression.append(":");
            operator = true;
            comma = false;
        }
    }

    public void bracketOpen() {
        if (result.length() != 0) deleteAll();
        expression.append("(");
        operator = false;
        brackets++;
    }

    public void bracketClose() {
        if (isLastBracket()) return;
        if (brackets == 0) return;
        if (isLastOperator()) return;
        expression.append(")");
        brackets--;
    }

    public void calculate() {
        if (isLastOperator()) {
            result.setLength(0);
            result.append("Fehlender Operand");
            return;
        }
        if (brackets != 0) {
            result.setLength(0);
            result.append("Ungültige Klammerung");
            return;
        }
        try {
            String expr = expression.toString();
            expr = expr.replace(',', '.').replace(':', '/');
            Expression exp = new ExpressionBuilder(expr).build();
            result.setLength(0);
            result.append(doubleFormat(exp.evaluate()).replace('.', ','));
        } catch (Throwable t) {
            result.setLength(0);
            result.append(t.getMessage());
        }
    }

    public void delete() {
        if (result.length() != 0) deleteAll();
        if (expression.length() != 0) {
            if (isLastOperator()) operator = false;
            expression.deleteCharAt(expression.length() - 1);
        }
    }

    public void deleteAll() {
        expression.setLength(0);
        result.setLength(0);
    }

    private String doubleFormat(double d) {
        if (d == (long) d)
            return String.format("%d", (long) d);
        else
            return String.format("%s", d);
    }

    private boolean isLastOperator() {
        if (expression.length() == 0) return false;
        switch (expression.charAt(expression.length() - 1)) {
            case '+':
                return true;
            case '-':
                return true;
            case '*':
                return true;
            case ':':
                return true;
            case ',':
                return true;
        }
        return false;
    }

    private boolean isLastBracket() {
        if (expression.length() != 0 && expression.charAt(expression.length() - 1) == '(')
            return true;
        return false;
    }

    private boolean isLastComma() {
        if (expression.length() == 0) return false;
        if (expression.charAt(expression.length() - 1) == ',')
            return true;
        return false;
    }

    public String toExpressionString() {
        return expression.toString();
    }

    public String toResultString() {
        return result.toString();
    }

    @Override
    public String toString() {
        return String.format("Ausdruck: %s\nErgebnis: %s",
                expression.toString(), result.toString());
    }
}
