import static java.util.concurrent.TimeUnit.SECONDS;

import java.time.LocalTime;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author kang
 * @since 2021/2/4
 */
public class ScheduledThreadPoolExecutorTest {

    private final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
    private final LongAdder count = new LongAdder();

    public void testDelayedTask() {
        System.out.println("开始测试时间" + LocalTime.now());
        count.increment();
        executor.scheduleWithFixedDelay(() -> {
            System.out.println("第" + count + "次开始时间: " + LocalTime.now());
            sleep(SECONDS, 10);
            System.out.println("第" + count + "次结束时间:" + LocalTime.now());
            count.increment();
        }, 1, 5, SECONDS);
    }

    public void testRateTask() {
        System.out.println("开始测试时间" + LocalTime.now());
        count.increment();
        executor.scheduleAtFixedRate(() -> {
            System.out.println("第" + count + "次开始时间" + LocalTime.now());
            sleep(SECONDS, 10);
            System.out.println("第" + count + "次结束时间" + LocalTime.now());
            count.increment();
        }, 1, 5, SECONDS);
    }

    private void sleep(TimeUnit unit, long time) {
        try {
            unit.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        new ScheduledThreadPoolExecutorTest().testDelayedTask();
        new ScheduledThreadPoolExecutorTest().testRateTask();
    }
}
