package sun.zhao.guo.exception;

import org.apache.commons.lang3.StringUtils;

/**
 *
 */
public enum ResultStat {
    /**
     * if you need new result code, add it here and give it a good name
     */

    OK(200, "服务正常"),

    PARAM_ERROR(400, "参数错误"),

    SERVER_INTERNAL_ERROR(500 , "服务异常"),

    MAX(9999, "系统异常"),

    LINUX_CMD_ERROR(301, "命令行执行异常");


    private int code;
    private String msg;

    public <T> HttpResponseTemp<T> wrap(T data) {
        return wrap(data, null);
    }

    /**
     * wrap the result with a result code and result message
     *
     * @param data result data
     * @param msg  result message
     * @param <T>  result type
     * @return
     */
    public <T> HttpResponseTemp<T> wrap(T data, String msg) {
        String message = this.name();
        if (!StringUtils.isEmpty(msg)) {
            message = message + ":" + msg;
        }
        return new HttpResponseTemp<>(data, this, message);
    }

    private ResultStat(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}