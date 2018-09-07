package com.douzi;

import com.douzi.calculator.operator.AddOperator;
import com.douzi.calculator.operator.MultiOperator;
import com.douzi.calculator.operator.Operator;

import java.math.BigDecimal;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {

        Operator operator = new AddOperator();
        BigDecimal bigDecimal = get();
        operator.calculate(new BigDecimal(11), new BigDecimal(10));

        operator = new MultiOperator();


        operator.calculate(new BigDecimal(11), bigDecimal);
        String str = "1+(15*6)-9890";
        for (String s : split(str)) {
            System.out.println(s);
        }
    }

    private static BigDecimal get() {
        if ("hehe".equals(System.getenv("hehe"))) {
            return new BigDecimal(10);
        }
        return null;
    }

    private static String[] split(String s) {
        String[] ss = null;
        StringBuilder strtmp = new StringBuilder();
        for (char x : s.toCharArray()) {
            if (x == '+' || x == '-' || x == '*' || x == '/' || x == ')' || x == '(') {
                strtmp.append(",");
                strtmp.append(x);
                strtmp.append(",");
            } else {
                strtmp.append(x);
            }
        }

        System.out.println(strtmp);
        System.out.println(strtmp.toString().replaceAll(",{2,}", ","));
        ss = strtmp.toString().replaceAll(",{2,}", ",").split(",");



        return ss;
    }
}
