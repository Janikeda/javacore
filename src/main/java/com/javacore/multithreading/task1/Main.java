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
        List<Thread> threads = Arrays.asList(new Thread(new FirstThread(foo)),
            new Thread(new SecondThread(foo)), new Thread(new ThirdThread(foo)));
        Collections.shuffle(threads);

        threads.forEach(Thread::start);
    }
}
