package cn.jack.netu.exception;

/**
 * created by Jack
 * date:19-5-5
 * describe:后台返回的数据格式为null,也就是空数据
 */
public class DataNullException extends RuntimeException {

    private int errStatus;
    private String errMessage;

    public DataNullException(int errStatus, String errMessage) {
        this.errStatus = errStatus;
        this.errMessage = errMessage;
    }

    public int getErrorStatus() {
        return errStatus;
    }

    public String getErrorMessage() {
        return errMessage;
    }
}