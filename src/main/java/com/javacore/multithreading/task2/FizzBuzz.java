package main.java.com.javacore.multithreading.task2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class FizzBuzz {

    private final int n;
    private final int three = 3;
    private final int five = 5;
    private int currNum = 1;
    private final CyclicBarrier cyclicBarrier = new CyclicBarrier(4);

    public FizzBuzz(int n) {
        this.n = n;
    }

    public void fizz() {
        Runnable runnable = () -> {
            if (currNum % three == 0 && currNum % five != 0) {
                System.out.println("fizz");
                currNum++;
            }
            waitHelper();
        };
        helper(runnable);
    }

    public void buzz() {
        Runnable runnable = () -> {
            if (currNum % five == 0 && currNum % three != 0) {
                System.out.println("buzz");
                currNum++;
            }
            waitHelper();
        };
        helper(runnable);
    }

    public void fizzbuzz() {
        Runnable runnable = () -> {
            if (currNum % three == 0 && currNum % five == 0) {
                System.out.println("fizzbuzz");
                currNum++;
            }
            waitHelper();
        };
        helper(runnable);
    }

    public void number() {
        Runnable runnable = () -> {
            if (currNum % three != 0 && currNum % five != 0) {
                System.out.println(currNum);
                currNum++;
            }
            waitHelper();
        };
        helper(runnable);
    }

    private void helper(Runnable runnable) {
        //Метод работает пока текущее число-счетчик не станет равным числу, заданному по условию
        while (currNum <= n) {
            //Выполнение логики публичных методов объекта
            runnable.run();
        }
    }

    private void waitHelper() {
        //Методы выполняют свою логику и ждут остальных. Как все 4 метода перейдут в ожидание, барьер снова откроется.
        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

}
