package main.java.com.javacore.multithreading.task2;

import java.util.concurrent.CountDownLatch;
import main.java.com.javacore.multithreading.task1.Foo;

public class FizzBuzzThread implements Runnable{

    private final FizzBuzz foo;

    public FizzBuzzThread(FizzBuzz foo) {
        this.foo = foo;
    }

    @Override
    public void run() {
        foo.fizzbuzz();
    }
}
