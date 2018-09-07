package com.douzi.calculator.operator;

import java.math.BigDecimal;

/**
 * @Author zouwei
 * @Datetime 2018-09-07 14:35
 * @Copyright 全国邮政电子商务运营中心
 */

public class SubOperator implements Operator {

    @Override
    public BigDecimal calculate(BigDecimal first, BigDecimal second) {
        return first.subtract(second);
    }

    @Override
    public int priority() {
        return 0;
    }
}
