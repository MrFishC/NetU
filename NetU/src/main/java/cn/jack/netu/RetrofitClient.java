package cn.jack.netu;

import android.content.Context;
import java.util.concurrent.TimeUnit;
import cn.jack.netu.interceptor.AddTokenInterceptor;
import cn.jack.netu.interceptor.ConnectivityInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @创建者 Jack
 * @创建时间 2023/7/14
 * @描述
 */
public class RetrofitClient {
    private static Retrofit retrofit = null;
    private static String baseUrl = null;
    private static Context context = null;

    private static OkHttpClient client = null;

    public static void init(Context context) {
        RetrofitClient.context = context;
    }

    public static void setBaseUrl(String baseUrl) {
        RetrofitClient.baseUrl = baseUrl;
    }

    public static void setClient(OkHttpClient client) {
        RetrofitClient.client = client;
    }

    public static Retrofit getClient() {
        if (baseUrl == null) {
            throw new RuntimeException("RetrofitClient's init function must be call frist!");
        }

        //非debug模式下,不添加日志拦截器
        HttpLoggingInterceptor loggerInterceptor = new HttpLoggingInterceptor(
                new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        //打印日志
                    }
                }
        );
        loggerInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//debug模式
//        loggerInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);//线上环境

        if (RetrofitClient.client == null) {
            RetrofitClient.client = new OkHttpClient.Builder()
                    .addInterceptor(new ConnectivityInterceptor(RetrofitClient.context))
                    .addInterceptor(new AddTokenInterceptor())
                    .addInterceptor(loggerInterceptor)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .build();
        }

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

