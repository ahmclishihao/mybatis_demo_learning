package com.lsh.demo;

/**
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-17
 */
public class LoopPrintTest {

    public static void main(String[] args) throws InterruptedException {
        new A().start();
        fewSleep();
        new B().start();

    }

    public static Object lock = new Object();

    public static boolean aRun = true;

    public static class A extends Thread {

        @Override
        public void run() {
            int i = 0;
            while(true){
                try {
                    synchronized (lock) {
                        while (!aRun) {
                            lock.wait();
                        }
                        System.out.print("\u001b[31m"+ (++i%10) + "\u001b[0m");
                        fewSleep();
                        aRun = false;
                        lock.notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static class B extends Thread {

        @Override
        public void run() {
            int i = 0;
            while(true){
                try {
                    synchronized(lock){
                        while(aRun){
                            lock.wait();
                        }
                        System.out.print("\u001b[32m" + (char) ('A' + (i++ % 26)) + "\u001b[0m");
                        fewSleep();
                        aRun = true;
                        lock.notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void fewSleep(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
