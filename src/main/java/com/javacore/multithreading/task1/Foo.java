package main.java.com.javacore.multithreading.task1;

import java.util.concurrent.CountDownLatch;

public class Foo {

    CountDownLatch cd1 = new CountDownLatch(1);
    CountDownLatch cd2 = new CountDownLatch(1);
    CountDownLatch cd3 = new CountDownLatch(1);


    public void first() { print("first"); }
    public void second() { print("second"); }
    public void third() { print("third"); }


    void print(String value) {
        System.out.print(value);
    }
}
