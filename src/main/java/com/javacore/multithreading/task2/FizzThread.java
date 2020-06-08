package main.java.com.javacore.multithreading.task2;

import java.util.concurrent.CountDownLatch;
import main.java.com.javacore.multithreading.task1.Foo;

public class FizzThread implements Runnable {

    private final FizzBuzz foo;

    public FizzThread(FizzBuzz foo) {
        this.foo = foo;
    }

    @Override
    public void run() {
        foo.fizz();
    }
}
