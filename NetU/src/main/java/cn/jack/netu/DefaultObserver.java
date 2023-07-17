package cn.jack.netu;

/**
 * @创建者 Jack
 * @创建时间 2023/7/16
 * @描述 这里给出了默认的对话框，已经加载失败时的默认操作，开发者也可以通过自定义CustomDisposableObserver的实现类自行实现
 */
public abstract class DefaultObserver<T> extends CustomDisposableObserver<T>{
    @Override
    protected void showLoding() {
        super.showLoding();

    }


    @Override
    protected void hideLoading() {
        super.hideLoading();

    }

    @Override
    void onFail(String msg) {

    }
}
