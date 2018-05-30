package com.lsh.demo;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-17
 */
public class ConsumerProducer {

    public static void main(String[] args) {
        new A().start();

        fewSleep();

        new B().start();
        new B().start();
    }

    public static List<Resource> resources = new LinkedList<>();

    public static int buffer_size = 10;

    public static AtomicInteger resourceCount = new AtomicInteger(0);

    public static Lock lock = new ReentrantLock();

    public static Condition produceCond = lock.newCondition();

    public static Condition consumeCond = produceCond;

    public static class A extends Thread {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    while (resourceCount.get() == buffer_size) {
                        produceCond.await();
                    }

                    producing();
                    resourceCount.getAndIncrement();

                    if (resourceCount.get() != 0) {
                        consumeCond.signalAll();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }
        }

        public void producing() {
            Resource resource = new Resource();
            resource.name = System.nanoTime() + " " + resourceCount.get();
            System.out.println(resource);
            resources.add(resource);
            fewSleep();
        }

    }

    public static class B extends Thread {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    while (resourceCount.get() == 0) {
                        consumeCond.await();
                    }

                    consuming();
                    resourceCount.getAndDecrement();

                    if (resourceCount.get() != buffer_size) {
                        produceCond.signalAll();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }
        }

        private void consuming() {
            Resource remove = resources.remove(0);
            remove.wasted = true;
            System.out.println(remove);
            fewSleep();
        }
    }

    public static class Resource {

        public String name;

        public boolean wasted = false;

        @Override
        public String toString() {
            return name + "  " + (wasted ? "已经消费" : "未消费");
        }

    }

    public static void fewSleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
