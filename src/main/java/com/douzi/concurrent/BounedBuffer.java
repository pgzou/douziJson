package com.douzi.concurrent;

/**
 * conditional queue
 * use Object.wait() Object.notifyAll()
 * in 《effective java》，not recommended，better use java.util.concurrent
 * @author zouwei
 * @date 2019-03-19
 */

public class BounedBuffer<V> extends BaseBoundedBuffer<V> {

    public BounedBuffer(int capacity) {
        super(capacity);
    }

    public synchronized V take() throws InterruptedException {
            while (isEmpty()) {
                this.wait(); // must in synchronized code block, or throw IllegalMonitorStateException
            }

            V v = doTake();
            this.notifyAll(); // notify() or notifyAll()?
            // better use notifyAll(), because notify() only notify one thread which may not the type you need
            // for example, after doTake(), if happen notify another take() thread, now some put() thread would not be notified
            return v;
    }

    public synchronized void put(V v) throws InterruptedException {
            while (isFull()) {
                this.wait();
            }

            doPut(v);
            this.notifyAll();
    }
}
