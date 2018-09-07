package com.douzi.calculator.operator;

import java.math.BigDecimal;

/**
 * @Author zouwei
 * @Datetime 2018-09-07 14:34
 * @Copyright 全国邮政电子商务运营中心
 */

public class AddOperator implements Operator {

    @Override
    public BigDecimal calculate(BigDecimal first, BigDecimal second) {
        return first.add(second);
    }

    @Override
    public int priority() {
        return 0;
    }
}
