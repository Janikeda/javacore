package main.java.com.javacore.multithreading.task2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    private final int playNumber = 15;
    public static void main(String[] args) {
        new Main().execute();
    }

    private void execute() {
        FizzBuzz fizzBuzz = new FizzBuzz(playNumber);
        List<Thread> threads = Arrays.asList(new Thread(new FizzThread(fizzBuzz)), new Thread(new BuzzThread(fizzBuzz)),
            new Thread(new FizzBuzzThread(fizzBuzz)), new Thread(new NumberThread(fizzBuzz)));
        Collections.shuffle(threads);
        threads.forEach(Thread::start);
    }

}
