package vip.jianjieming.LeetCode.multithreading.按序打印;

/**
 * @author jianjieming
 * @date 2019/12/24 16:34
 */
public class FooMain {
    public static void main(String[] args) {
        Foo foo = new Foo();
        Thread thread1 = new Thread(() -> {
            try {
                foo.first(() -> System.out.println("one"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                foo.second(() -> System.out.println("two"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread3 = new Thread(() -> {
            try {
                foo.third(() -> System.out.println("third"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
