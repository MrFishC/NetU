package cn.jack.netu.exception;

/**
 * @创建者 Jack
 * @创建时间 2023/7/14
 * @描述
 */

public class ApiException extends Exception {
    public int code;
    public String message;

    public ApiException(String message) {
        super(message);
        this.message = message;
    }

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }
}


