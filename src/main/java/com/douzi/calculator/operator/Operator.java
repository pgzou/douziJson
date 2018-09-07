package com.douzi.calculator.operator;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * math Operator like + - * /
 * @Author douzi[pg-zouwei@qq.com]
 */

public interface Operator {

    Map<String, Operator> oprators = Factory.instance();

    /**
     * calculate first operator second such as 1 + 2
     * @param first
     * @param second
     * @return
     */
    @NotNull
    BigDecimal calculate(@NotNull BigDecimal first, @NotNull BigDecimal second);

    int priority();



    class Factory {
        public static Map<String, Operator> instance() {
            Map<String, Operator> instance = new HashMap();

            instance.put("+", new AddOperator());
            instance.put("-", new SubOperator());
            instance.put("*", new MultiOperator());
            instance.put("/", new DiviOperator());

            return instance;
        }
    }

}
