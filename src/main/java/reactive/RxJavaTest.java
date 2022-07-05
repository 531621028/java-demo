package reactive;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;
import utils.LogUtils;

/**
 * @author kang
 * @date 2022/2/22
 */
public class RxJavaTest {

    public static void main(String[] args) throws InterruptedException {
        Observable
            .create((ObservableOnSubscribe<Integer>) emitter -> {
                LogUtils.log("emitter");
                emitter.onNext(1);
                emitter.onComplete();
            })
            .observeOn(Schedulers.computation())
            .subscribeOn(Schedulers.io())
            // .observeOn(Schedulers.computation()) observeOn在subscribeOn前后的顺序不会影响Observer执行的线程
            .map(item -> {
                LogUtils.log("before subscribe");
                return item;
            })
            .map(item -> item * 2)
            .subscribe((res) -> LogUtils.log("after subscribe"));
        Thread.sleep(1000);
    }
}
