package main.java.com.javacore.multithreading.task1;

import java.util.concurrent.CountDownLatch;

public class ThirdThread implements Runnable{

    private final Foo foo;
    private final CountDownLatch cd;

    public ThirdThread(Foo foo, CountDownLatch cd) {
        this.foo = foo;
        this.cd = cd;
    }

    @Override
    public void run() {
        try {
            cd.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        foo.third();
    }
}
