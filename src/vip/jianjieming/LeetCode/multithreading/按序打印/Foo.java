package vip.jianjieming.LeetCode.multithreading.按序打印;

import java.util.concurrent.CountDownLatch;

/**
 * @author jianjieming
 * @date 2019/12/24 16:36
 */
class Foo {

    private CountDownLatch countDownLatchA;
    private CountDownLatch countDownLatchB;

    public Foo() {
        countDownLatchA = new CountDownLatch(1);
        countDownLatchB = new CountDownLatch(1);
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        countDownLatchA.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        countDownLatchA.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        countDownLatchB.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        countDownLatchB.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
