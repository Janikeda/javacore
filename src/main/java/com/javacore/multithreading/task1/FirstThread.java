package main.java.com.javacore.multithreading.task1;

import java.util.concurrent.CountDownLatch;

public class FirstThread implements Runnable {

    private final Foo foo;
    private final CountDownLatch cd;

    public FirstThread(Foo foo, CountDownLatch cd) {
        this.foo = foo;
        this.cd = cd;
    }

    @Override
    public void run() {
        foo.first();
        cd.countDown();
    }
}
