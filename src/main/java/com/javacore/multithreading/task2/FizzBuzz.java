package main.java.com.javacore.multithreading.task2;

import java.util.concurrent.locks.ReentrantLock;

public class FizzBuzz {

    private final int n;
    private int currNum = 1;
    private final ReentrantLock lock = new ReentrantLock();

    public FizzBuzz(int n) {
        this.n = n;
    }

    public void fizz() {
        while (currNum <= n) {
            lock.lock();
            try {
                if (currNum > n) {
                    return;
                }
                if (currNum % 3 == 0 && currNum % 5 != 0) {
                    System.out.println("fizz");
                    currNum++;
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public void buzz() {
        while (currNum <= n) {
            lock.lock();
            try {
                if (currNum > n) {
                    return;
                }
                if (currNum % 5 == 0 && currNum % 3 != 0) {
                    System.out.println("buzz");
                    currNum++;
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public void fizzbuzz() {
        while (currNum <= n) {
            lock.lock();
            try {
                if (currNum > n) {
                    return;
                }
                if (currNum % 3 == 0 && currNum % 5 == 0) {
                    System.out.println("fizzbuzz");
                    currNum++;
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public void number() {
        while (currNum <= n) {
            lock.lock();
            try {
                if (currNum > n) {
                    return;
                }
                if (currNum % 3 != 0 && currNum % 5 != 0) {
                    System.out.println(currNum);
                    currNum++;
                }
            } finally {
                lock.unlock();
            }
        }
    }

}
