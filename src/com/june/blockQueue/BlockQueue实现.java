package com.june.blockQueue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockQueue实现<T> {
    private int size;
    private int count;
    private Deque<T> container ;
     Lock lock = new ReentrantLock();
    Condition isNull = lock.newCondition();
    Condition isFull = lock.newCondition();

    public BlockQueue实现(int size) {
        container = new LinkedList<>();
        this.size = size;
    }

    public void put(T x) throws InterruptedException {
        lock.lock();
        try {
            while (count == size) {
                System.out.println("队列已满请等待");
                isFull.await();
            }
            count++;
            container.offer(x);
            isNull.signal();
        } finally {
            lock.unlock();
        }
    }
    public T take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                System.out.println("队列已满请等待");
                isNull.await();
            }
            count--;
            T res = container.poll();
            isFull.signal();
            return res;
        } finally {
            lock.unlock();
        }
    }
}
