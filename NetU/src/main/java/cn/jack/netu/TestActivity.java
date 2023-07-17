package cn.jack.netu;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import autodispose2.AutoDispose;
import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @创建者 Jack
 * @创建时间 2023/7/16
 * @描述
 */
public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        test();
    }

    private void test() {

        ApiManager.getInstance()
                .obtainRetrofitService(ApiService.class)
                .getBannerImages()
                //拦截服务器返回的错误
                .map(new RxFunction<String>())
                .compose(RxUtils.applySchedulers())
                .compose(RxUtils.handleErrors())
                //处理rxjava的内存泄漏
                .to(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new DefaultObserver<String>() {

                    @Override
                    void onSuccess(String s) {

                    }

                });

//        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//        ProgressRequestBody progressRequestBody = new ProgressRequestBody(requestBody, new ProgressRequestBody.ProgressListener() {
//            @Override
//            public void onProgress(long bytesWritten, long contentLength) {
//
//            }
//        });
//        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), progressRequestBody);

    }

}
