package reactive;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Consumer;
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
        // windows 类似于flatMap的反操作，产生一个Observable<Observable<Integer>>
        Observable.just(1, 2, 3, 4, 5, 6, 7).window(3).subscribe(new Consumer<Observable<Integer>>() {
            @Override
            public void accept(Observable<Integer> integerObservable) throws Throwable {

            }
        });
        // buffer 将原始对象中的序列进行缓存，达到一定数量再统一发送
        Observable.just(1, 2, 3, 4, 5, 6, 7).buffer(3).subscribe(integers -> {

        });
        Thread.sleep(1000);
    }
}
