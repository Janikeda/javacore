package main.java.com.javacore.multithreading.task1;

public class SecondThread implements Runnable{

    private final Foo foo;

    public SecondThread(Foo foo) {
        this.foo = foo;
    }

    @Override
    public void run() {
        foo.second();
    }
}
