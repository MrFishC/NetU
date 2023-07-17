package cn.jack.netu;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @创建者 Jack
 * @创建时间 2023/7/14
 * @描述 线程切换 + 错误处理
 * <p>
 * 在RxFunction类中使用map进行数据剥壳，根据后台接口返回的状态值决定是否需要抛出异常或返回数据。抛出的异常将传递给onErrorResumeNext；
 * onErrorResumeNext：流在事件传递过程中有错误发生，可以将一个新的流交给onErrorResumeNext操作符进行处理；
 * <p>
 * ExceptionEngine中的逻辑需要结合实际情况进行自定义
 */
public class RxUtils {

    /**
     * 这个 applySchedulersWithErrorHandling() 方法用于将一组常用的操作符（subscribeOn(), observeOn() 和 onErrorResumeNext()）打包到一个 ObservableTransformer 中。
     * 这样，你可以使用 compose() 操作符将这些操作符应用到多个 Observable 上，而不需要每次都重复这些代码。这个方法使用泛型 T，以便可以应用于任何类型的 Observable。
     */
//    public static <T> ObservableTransformer<T, T> applySchedulersWithErrorHandling() {
//        return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).onErrorResumeNext(throwable -> {
//            // 可以选择在该位置进行相应的错误处理，也可以创建新的流使其继续传递下去
//            //如：token失效或者其它情况，在这里添加逻辑
//
//            //下方的返回的流，最终会触发自定义的DisposableObserver实现类的onError回调
//            return Observable.error(ExceptionEngine.handleException(throwable));
//        });
//    }

    //抽离成单独的方法，责任分离[非lambda形式的写法]
    public static <T> ObservableTransformer<T, T> applySchedulers() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> ObservableTransformer<T, T> handleErrors() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.onErrorResumeNext(new Function<Throwable, ObservableSource<? extends T>>() {
                    @Override
                    public ObservableSource<? extends T> apply(Throwable throwable) {
                        return Observable.error(ExceptionEngine.handleException(throwable));
                    }
                });
            }
        };
    }

}


