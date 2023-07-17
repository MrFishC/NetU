package cn.jack.netu;

/**
 * @创建者 Jack
 * @创建时间 2023/7/15
 * @描述 根据后台约定进行内部格式的定义
 */
public class ApiResponse<T> {
    private int code;
    private int stat;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isOk() {
        return stat == 1;
    }

    public boolean isEmpty() {
        return data == null;
    }

    public boolean isTokenInvalid() {
        return stat == 999;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "code='" + code + '\'' +
                ", stat=" + stat +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
