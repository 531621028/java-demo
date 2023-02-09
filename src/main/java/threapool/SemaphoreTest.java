package threapool;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {

    public static void main(String[] args) {
        int permitsNum = 2;
        // release会添加令牌，并不会以初始化的大小为准。Semaphore中release方法的调用并没有限制要在acquire后调用。只要获取到足够的令牌就可以直接运行
        final Semaphore semaphore = new Semaphore(permitsNum);
        try {
            System.out.println(
                "availablePermits:" + semaphore.availablePermits() + ",semaphore.tryAcquire(3,1, TimeUnit.SECONDS):"
                    + semaphore.tryAcquire(3, 1, TimeUnit.SECONDS));
            semaphore.release();
            System.out.println(
                "availablePermits:" + semaphore.availablePermits() + ",semaphore.tryAcquire(3,1, TimeUnit.SECONDS):"
                    + semaphore.tryAcquire(3, 1, TimeUnit.SECONDS));
        } catch (Exception e) {

        }
    }
}