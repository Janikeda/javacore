package main.java.com.javacore.multithreading.task2;

public class BuzzThread implements Runnable{

    private final FizzBuzz foo;


    public BuzzThread(FizzBuzz foo) {
        this.foo = foo;
    }

    @Override
    public void run() {
        foo.buzz();
    }
}
