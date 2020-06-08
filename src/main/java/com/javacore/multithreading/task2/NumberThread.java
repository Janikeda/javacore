package main.java.com.javacore.multithreading.task2;

public class NumberThread implements Runnable {

    private final FizzBuzz foo;


    public NumberThread(FizzBuzz foo) {
        this.foo = foo;
    }

    @Override
    public void run() {
        foo.number();
    }
}
