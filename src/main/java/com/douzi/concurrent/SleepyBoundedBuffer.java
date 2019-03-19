package com.douzi.concurrent;

/**
 * sleep when take() from empty buffer or put() into full buffer
 * @author zouwei
 * @date 2019-03-19
 */

public class SleepyBoundedBuffer<V> extends BaseBoundedBuffer<V> {

    private final long SLEEP_GRANULARITY = 10;

    public SleepyBoundedBuffer(int capacity) {
        super(capacity);
    }

    public V take() throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (!isEmpty()) {
                    return doTake();
                }
            }
            Thread.sleep(SLEEP_GRANULARITY);
        }
    }

    public void put(V v) throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (!isFull()) {
                    doPut(v);
                    return;
                }
            }
            Thread.sleep(SLEEP_GRANULARITY);
        }
    }

}
