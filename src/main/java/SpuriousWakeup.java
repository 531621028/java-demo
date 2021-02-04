import java.util.concurrent.TimeUnit;

/**
 * @author kang
 * @since 2021/1/30 虚假唤醒的例子
 *
 * wait方法可以分为三个操作： （1）释放锁并阻塞 （2）等待条件cond发生，进入等待队列 （3）获取通知后，竞争获取锁
 */
public class SpuriousWakeup {

    public static class Stack {

        // TOTAL表示最大可以容纳的总量
        private static final int TOTAL = 1; //数字取1是为了放大问题
        private int num = 0; //店里当前的货物量

        public synchronized void pop() throws InterruptedException {
            while (num != TOTAL) {
                System.out.println("等待之前");
                wait(); // 进入wait操作之后，当重新获取到锁之后不会再对前面的条件进行判断
                sleep(200L);
                System.out.println("等待之后");
            }
            System.out.println("wait操作执行完成之后执行");
        }

        public synchronized void add() {
            num++;
            System.out.println("唤醒操作");
            notifyAll();
        }

        private void sleep(long timeout) {
            try {
                TimeUnit.MICROSECONDS.sleep(timeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {

        final Stack stack = new Stack();
        new Thread(new Runnable() {
            public void run() {
                try {
                    stack.pop();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                stack.add();
            }
        }).start();
        stack.num--;
    }

}
