package main.java.com.javacore.multithreading.task1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Main {

    public static void main(String[] args) {
        new Main().execute();
    }

    private void execute() {
        Foo foo = new Foo();
        CountDownLatch cd = new CountDownLatch(1);
        CountDownLatch cd2 = new CountDownLatch(1);
        List<Thread> threads = Arrays.asList(new Thread(new FirstThread(foo, cd)),
            new Thread(new SecondThread(foo, cd, cd2)), new Thread(new ThirdThread(foo, cd2)));
        Collections.shuffle(threads);

        threads.forEach(Thread::start);
    }
}
