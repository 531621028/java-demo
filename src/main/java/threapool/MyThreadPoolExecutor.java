package threapool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author kang
 * @since 2021/3/28
 */
public class MyThreadPoolExecutor implements Executor {

    /**
     * 线程池的名称
     */
    private final String name;
    /**
     * 核心线程数
     */
    private final int coreSize;
    /**
     * 最大线程数
     */
    private final int maxSize;
    /**
     * 任务队列
     */
    private final BlockingQueue<Runnable> taskQueue;
    /**
     * 拒绝策略
     */
    private final RejectPolicy rejectPolicy;

    private final AtomicInteger runningCount = new AtomicInteger();
    private final AtomicInteger sequence = new AtomicInteger(1);

    public MyThreadPoolExecutor(String name, int coreSize, int maxSize,
        BlockingQueue<Runnable> taskQueue, RejectPolicy rejectPolicy) {
        this.name = name;
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.taskQueue = taskQueue;
        this.rejectPolicy = rejectPolicy;
    }


    @Override
    public void execute(Runnable command) {
        int runningCnt = this.runningCount.get();
        if (runningCnt < coreSize) {
            if (addWorker(command, true)) {
                return;
            }
        }
        if (!taskQueue.offer(command)) {
            // 入队失败的操作
            if (!addWorker(command, false)) {
                rejectPolicy.reject(command, this);
            }
        }

    }

    private boolean addWorker(Runnable newTask, boolean core) {
        while (true) {
            int runningCnt = runningCount.get();
            int max = core ? coreSize : maxSize;
            if (runningCnt >= max) {
                return false;
            }
            if (runningCount.compareAndSet(runningCnt, runningCnt + 1)) {
                startWorker(newTask, core);
                break;
            }
        }
        return true;
    }

    private void startWorker(Runnable newTask, boolean core) {
        // 线程的名字
        String threadName = name + (core ? "core_" : "") + sequence.incrementAndGet();
        new Thread(() -> {
            Runnable task = newTask;
            while (task != null || (task = getTask()) != null) {
                try {
                    System.out.println("thread name: " + Thread.currentThread().getName());
                    // 执行任务
                    task.run();
                } finally {
                    // 任务执行完成，置为空
                    task = null;
                }
            }
        }, threadName).start();
    }

    private Runnable getTask() {
        try {
            return taskQueue.poll(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            // 线程中断了，返回null可以结束当前线程
            // 当前线程都要结束了，理应要把runningCount的数量减一
            runningCount.decrementAndGet();
            return null;
        }
    }
}
