import static java.lang.Thread.sleep;

import org.apache.commons.lang3.RandomUtils;

/**
 * @author kang
 * @since 2021/1/30 虚假唤醒的例子 当一定的条件触发时会唤醒很多在阻塞态的线程，但只有部分的线程唤醒是有用的，其余线程的唤醒是多余的。唤醒之后需要重复检查判断条件，因为判断条件的状态可能发生的变化
 * wait方法可以分为三个操作： （1）释放锁并阻塞 （2）等待条件cond发生，进入等待队列 （3）获取通知后，竞争获取锁
 */
public class SpuriousWakeup {

    public static class Stack {

        // TOTAL表示最大可以容纳的总量
        private volatile int num = 0; //店里当前的货物量

        public synchronized void pop() throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + "开始pop" + num);
            // 使用if有可能会出现超量消费,正确的是使用while来循环判断条件是否满足要求
            while (num == 0) {
                wait(); // 进入wait操作之后，当重新获取到锁之后不会再对前面的条件进行判断
                System.out.println(Thread.currentThread().getName() + "被唤醒之后");
            }
            System.out.println(Thread.currentThread().getName() + "结束pop" + num);
            num = num - 1;
            sleep(RandomUtils.nextInt(0, 100));
            notifyAll();
        }

        public synchronized void add() throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + "开始push" + num);
            // 使用if有可能会出现超量生产,正确的是使用while来循环判断条件是否满足要求
            while (num > 0) {
                wait();
                System.out.println(Thread.currentThread().getName() + "被唤醒之后");
            }
            num = num + 1;
            System.out.println(Thread.currentThread().getName() + "结束push" + num);
            sleep(RandomUtils.nextInt(0, 100));
            notifyAll();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        final Stack stack = new Stack();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    stack.pop();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "消费者1").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    stack.pop();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "消费者2").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    stack.add();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "生产者1").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    stack.add();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "生产者2").start();
    }
}
