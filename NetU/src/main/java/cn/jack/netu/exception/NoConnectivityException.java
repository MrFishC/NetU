package cn.jack.netu.exception;

/**
 * created by Jack
 * date:19-5-5
 * describe:后台返回的数据格式为null,也就是空数据
 */
public class NoConnectivityException extends RuntimeException {

    private String errMessage;

    public NoConnectivityException(String errMessage) {
        this.errMessage = errMessage;
    }

}