package threapool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class MyThreadPoolFutureExecutorTest {

    public static void main(String[] args) {
        FutureExecutor threadPool = new MyThreadPoolFutureExecutor("test", 2, 4,
            new ArrayBlockingQueue<>(6), new DiscardRejectPolicy());
        List<MyFuture<Integer>> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            int num = i;
            MyFuture<Integer> future = threadPool.submit(() -> {
                Thread.sleep(1000);
                System.out.println("running: " + num);
                return num;
            });
            list.add(future);
        }

        for (MyFuture<Integer> future : list) {
            System.out.println("finished: " + future.get());
        }
    }
}