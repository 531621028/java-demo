package threapool;

import java.util.concurrent.Executor;

/**
 * @author kang
 * @since 2021/3/28
 */
public interface RejectPolicy {

    void reject(Runnable task, Executor executor);

}
