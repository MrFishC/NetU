package cn.jack.netu;

import cn.jack.netu.exception.ApiException;
import cn.jack.netu.exception.DataNullException;
import cn.jack.netu.exception.TokenInvalidException;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.functions.Function;

/**
 * @创建者 Jack
 * @创建时间 2023/7/15
 * @描述 数据剥壳 + 异常抛出[异常的抛出也可以通过拦截器的方式实现]
 */
public class RxFunction<T> implements Function<ApiResponse<T>, T> {

    @Override
    public T apply(@NonNull ApiResponse<T> apiResponse) throws ApiException {

        //拦截服务器返回的数据，根据实际情况抛出不同的异常等
        if (apiResponse.isOk()) {
            if (apiResponse.isEmpty()) {
                //rxjava(对空数据的处理) 不允许发射null数据源      在这里抛出异常的方案 最合适
                throw new DataNullException(apiResponse.getStat(), apiResponse.getMsg());
            }

            return apiResponse.getData();
        } else if (apiResponse.isTokenInvalid()) {
            throw new TokenInvalidException(apiResponse.getStat(), apiResponse.getMsg());
        } else {
            ApiException apiException = new ApiException(apiResponse.getMsg());
            apiException.code = apiResponse.getCode();
            throw apiException;
        }


        //视约定而定 TokenInvalidException
//        if(status == NetConfig.FAIL){
//            throw new APIException(apiResponse.getStat(), apiResponse.getMsg());
//        }else if(status == NetConfig.TOKEN_INVALID){
//            throw new TokenInvalidException(apiResponse.getStat(),apiResponse.getMsg());
//        }else if(status == NetConfig.PASSWD_INVALID){
//            throw new PasswdInvalidException(apiResponse.getStat(),apiResponse.getMsg());
//        }else if(status == NetConfig.UPDATE_PASSWD){
//            throw new UpdatePasswdException(apiResponse.getStat(),apiResponse.getMsg());
//        }else if(status == NetConfig.TIME_OUT){
//            throw new TimeOutException(apiResponse.getStat(),apiResponse.getMsg());
//        }

    }
}
