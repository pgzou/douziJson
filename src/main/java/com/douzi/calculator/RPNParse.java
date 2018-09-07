package com.douzi.calculator;

import com.douzi.calculator.operator.Operator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author douzi[pg-zouwei@qq.com]
 */

public class RPNParse {

    private final static char DECIMAL_POINT = '.';
    private final static char LEFT_PARENT = '(';
    private final static char RIGHT_PARENT = ')';
    private final static char MINUS_SIGN = '-';
    private final static char BLANK = ' ';

    /**
     * parse math expression to RPN expression（逆波兰表达式）
     * @param
     * @return
     */
    public List<String> parseRPN(String expression) {

        List<String> result = new ArrayList<>();
        Stack<Character> optStack = new Stack();
        StringBuilder number = new StringBuilder();
        char[] chars = expression.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            // number
            if ((c >= '0' && c<= '9') || c == DECIMAL_POINT) {
                number.append(c);
            }else {
                if (number.length() > 0) {
                    if (number.charAt(0) == DECIMAL_POINT || number.charAt(number.length() - 1) == DECIMAL_POINT) {
                        throw new IllegalArgumentException("decimal point should in the middle of number");
                    }
                    result.add(number.toString());
                    number.setLength(0); // clear number sb
                }
                if (c == BLANK) {
                    continue;
                }else if (c == MINUS_SIGN) {
                    number.append(c);
                }else if (c == LEFT_PARENT) {
                    optStack.push(c);
                }else if (c == RIGHT_PARENT) {
                    if (!optStack.contains(LEFT_PARENT)) {
                        throw new IllegalArgumentException("lack of ( ");
                    }
                    char opt = optStack.pop();

                    while (opt != LEFT_PARENT) {
                        result.add(String.valueOf(opt));
                        opt = optStack.pop();
                    }
                }else if (isOperator(c)) {
                    push(result, optStack, c);
                }else {
                    throw new IllegalArgumentException("illegal symbol：" + c);
                }

            }
        }

        // last number
        if (number.length() > 0) {
            result.add(number.toString());
        }

        // the rest of operator
        while(optStack.size() > 0) {
            if (optStack.peek() != LEFT_PARENT) {
                result.add(String.valueOf(optStack.pop()));
            }else {
                optStack.pop();
            }
        }

        return result;
    }

    private void push(List<String> result, Stack<Character> optStack, char c) {
        if (optStack.size() == 0 || optStack.peek() == LEFT_PARENT
                || priority(c, optStack.peek())) {
            optStack.push(c);
        }else {
            result.add(String.valueOf(optStack.pop()));
            push(result, optStack, c);
        }
    }

    private boolean isOperator(char c) {
        return Operator.oprators.containsKey(String.valueOf(c));
    }

    private boolean priority(char first, char second) {
        Operator firstOpt = Operator.oprators.get(String.valueOf(first));
        Operator secondOpt = Operator.oprators.get(String.valueOf(second));
        return firstOpt.priority() > secondOpt.priority();
    }
}
