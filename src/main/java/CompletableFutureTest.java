import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author kang
 * @since 2021/2/21
 */
public class CompletableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int i = 1 / 0;
            return 100;
        });
        future.join();
//        future.get();
    }

}
