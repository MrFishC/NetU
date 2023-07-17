package cn.jack.netu.exception;

/**
 * created by Jack
 * date:19-5-7
 * describe:token失效(帐号在其它设备登录/或其它原因导致token失效,其它因素导致token失效),抛出异常.
 */
public class TokenInvalidException extends RuntimeException {

    private int errStatus;
    private String errMessage;

    public TokenInvalidException(int errStatus, String errMessage) {
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

