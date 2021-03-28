package threapool;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

public interface FutureExecutor extends Executor {

    <T> MyFuture<T> submit(Callable<T> command);
}