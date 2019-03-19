package com.douzi.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * condition
 * @author zouwei
 * @date 2019-03-19
 */

public class ConditionBoundedBuffer<V> {

    private ReentrantLock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    private final V[] buf;
    private int tail;
    private int head;
    private int count;

    public ConditionBoundedBuffer(int capacity) {
        this.buf = (V[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == buf.length;
    }

    public void put(V v) throws InterruptedException {
        lock.lock();
        try {
            while (isFull()) {
                notFull.await();
            }
            buf[tail] = v;

            if (++tail == buf.length) {
                tail = 0;
            }
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public V take() throws InterruptedException {
        lock.lock();
        try {
            while (isEmpty()) {
                notEmpty.await();
            }
            V v = buf[head];
            buf[head] = null;
            if (++head == buf.length) {
                head = 0;
            }
            --count;
            notFull.signal();
            return v;
        } finally {
            lock.unlock();
        }
    }
}
