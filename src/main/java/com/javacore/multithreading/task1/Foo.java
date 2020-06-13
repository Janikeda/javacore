package main.java.com.javacore.multithreading.task1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Foo {

    private final CountDownLatch cd = new CountDownLatch(2);
    private final int timeout = 10;

    public void first() {
        print("first");
        cd.countDown();
    }
    public void second() {
        try {
            cd.await(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        print("second");
        cd.countDown();
    }
    public void third() {
        try {
            cd.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        print("third"); }


    void print(String value) {
        System.out.print(value);
    }
}
