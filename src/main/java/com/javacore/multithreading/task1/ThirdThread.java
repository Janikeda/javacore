package main.java.com.javacore.multithreading.task1;

public class ThirdThread implements Runnable{

    private final Foo foo;

    public ThirdThread(Foo foo) {
        this.foo = foo;
    }

    @Override
    public void run() {
        foo.third();
    }
}
