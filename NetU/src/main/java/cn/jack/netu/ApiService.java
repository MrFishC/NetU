package cn.jack.netu;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

/**
 * @创建者 Jack
 * @创建时间 2023/7/15
 * @描述
 */
public interface ApiService {
    @GET("system/v1/banner/getImage")
    Observable<ApiResponse<String>> getBannerImages();
}
