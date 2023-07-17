package cn.jack.netu.interceptor;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @创建者 Jack
 * @创建时间 2023/7/16
 * @描述
 *
 * token 拦截器
 *
 *  todo 其它拦截器 对指定的接口 进行 参数加密 响应解密  https://blog.csdn.net/qq122627018/article/details/68957782
 */
public class AddTokenInterceptor implements Interceptor {

//    private final String TOKEN = "token";   //字符串中的内容需要跟后台约定      位置1

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
//        String url = request.url().toString();
//        if (needAddToken(url)) {
//            String token = "获取存储的token";              //位置2
//            Request updateRequest = request.newBuilder().header(TOKEN, token).build();
//            return chain.proceed(updateRequest);
//        }

        return chain.proceed(request);
    }

    private boolean needAddToken(String url) {
        //添加不需要token的api
        return !url.contains("xxx/login") || !url.contains("xxx/login1");
    }

}

