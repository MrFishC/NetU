package cn.jack.netu;

/**
 * @创建者 Jack
 * @创建时间 2023/7/15
 * @描述
 */
public class ApiManager {
    private ApiManager() {

    }

    /**
     * 单例
     */
    public static ApiManager getInstance() {
        return ApiManager.Holder.INSTANCE;
    }

    private static class Holder {
        private static final ApiManager INSTANCE = new ApiManager();
    }

    public <T> T obtainRetrofitService(Class<T> service) {
        return RetrofitClient.getClient().create(service);
    }
}
