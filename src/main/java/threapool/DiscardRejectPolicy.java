package threapool;

import java.util.concurrent.Executor;

public class DiscardRejectPolicy implements RejectPolicy {

    @Override
    public void reject(Runnable task, Executor executor) {
        // do nothing
        System.out.println("discard one task");
    }
}