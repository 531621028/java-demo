import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author kang
 * @since 2021/3/17
 */
public class DelayQueueTest {

    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayImpl> delayQueue = new DelayQueue<>();
        delayQueue.add(new DelayImpl());
        new Thread(()->{
            try {
                System.out.println(delayQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static class DelayImpl implements Delayed{
        @Override
        public long getDelay(TimeUnit unit) {
            return 1000;
        }

        @Override
        public int compareTo(Delayed o) {
            return 0;
        }
    }

}
