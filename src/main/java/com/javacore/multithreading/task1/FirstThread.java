package main.java.com.javacore.multithreading.task1;

public class FirstThread implements Runnable {

    private final Foo foo;

    public FirstThread(Foo foo) {
        this.foo = foo;
    }

    @Override
    public void run() {
        foo.first();
    }
}
