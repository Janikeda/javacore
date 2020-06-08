package main.java.com.javacore.multithreading.task1;

import java.util.concurrent.CountDownLatch;

public class SecondThread implements Runnable{

    private final Foo foo;
    private final CountDownLatch cd;
    private final CountDownLatch cd2;

    public SecondThread(Foo foo, CountDownLatch cd, CountDownLatch cd2) {
        this.foo = foo;
        this.cd = cd;
        this.cd2 = cd2;
    }

    @Override
    public void run() {
        try {
            cd.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        foo.second();
        cd2.countDown();
    }
}
