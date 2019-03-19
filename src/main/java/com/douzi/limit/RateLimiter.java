package com.douzi.limit;

import com.google.common.base.Preconditions;

/**
 * 最简单的限速器
 * @Author zouwei
 * @Datetime 2018-12-28 11:27
 */

public class RateLimiter {

    private int limit;
    private long[] time;
    private int position;

    public RateLimiter(int limit) {
        Preconditions.checkArgument(limit > 0, "limit must >0");
        this.limit = limit;
        time = new long[limit];
    }

    public void qdsControl() {
        checkReverse();

        long now = System.currentTimeMillis();
        if (time[position] == 0) {
            doSomething();
            time[position] = now;
            position++;
        } else if (time[position] < now - 1000) {
            doSomething();
            time[position] = now;
            position++;
        } else {
            System.out.println("beyond limit!!!");
        }

    }

    private void checkReverse() {
        if (position >= limit) {
            position = 0;
        }
    }

    public void doSomething() {

    }


    public static void main(String[] args) throws InterruptedException {
        RateLimiter limiter = new RateLimiter(10);

        for (int i = 0; i < 9; i++) {
            limiter.qdsControl();
        }
        Thread.sleep(100);
        for (int i = 0; i < 2; i++) {
            limiter.qdsControl();
        }
        Thread.sleep(1000);
        for (int i = 0; i < 11; i++) {
            limiter.qdsControl();
        }
    }
}
