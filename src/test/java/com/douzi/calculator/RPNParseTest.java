package com.douzi.calculator;

import org.junit.Test;

/**
 * @Author zouwei
 * @Datetime 2018-09-07 18:18
 * @Copyright 全国邮政电子商务运营中心
 */

public class RPNParseTest {

    @Test
    public void parseRPN() throws Exception {
        RPNParse parse = new RPNParse();
        String expression = "((10.1 * (6 / ((9 + 3) * -11))) + 17) + 5";
        System.out.println(parse.parseRPN(expression));
    }

}