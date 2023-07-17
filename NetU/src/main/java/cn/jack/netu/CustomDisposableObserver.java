package cn.jack.netu;

import cn.jack.netu.exception.ApiException;
import cn.jack.netu.exception.DataNullException;
import cn.jack.netu.exception.NoConnectivityException;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;

/**
 * @创建者 Jack
 * @创建时间 2023/7/14
 * @描述
 */
public abstract class CustomDisposableObserver<T> extends DisposableObserver<T> {

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onNext(@NonNull T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ApiException) {
            onFail(e.getMessage());
        } else if (e instanceof DataNullException) {
            onEmpty();
        } else if (e instanceof NoConnectivityException) {
            onNetError();
        } else {
            onFail(e.getMessage());
        }
    }

    @Override
    public void onComplete() {

    }

    abstract void onSuccess(T t);

    abstract void onFail(String msg);

    protected void onEmpty() {

    }

    protected void onNetError() {

    }

    protected void showLoding() {

    }

    protected void hideLoading() {

    }
}

