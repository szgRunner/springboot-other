package sun.zhao.guo.guo.common;

import java.io.Serializable;
import java.util.StringJoiner;

public class Result<T> implements Serializable {
    public Boolean success;
    public String resource;
    public String code;
    public String msg;
    public T data;

    public Result() {
        success = false;
    }


    public Result(Boolean success, String code, String msg, T data) {
        this(success, code, msg, data, null);
    }

    public Result(Boolean success, String code, String msg, T data, String resource) {
        super();
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.resource = resource;
    }

    public void setBoolean(boolean b) {
        this.success = b;
    }

    public void setSuccess(T t) {
        this.data = t;
        this.success = true;
    }

    public void setUnSuccess(String msg, T t) {
        this.data = t;
        this.success = false;
        this.msg = msg;
    }

    public void setUnSuccess(String msg) {
        this.success = false;
        this.msg = msg;
    }

    /**
     * 空 或 null 的情况下使用
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Result<T> npeErrorResult(String msg) {
        return new Result<>(false, "", msg, null);
    }

    /**
     * OK
     *
     * @return
     */
    public static <T> Result<T> OkMsg() {
        return OkMsg("");
    }

    /**
     * OK
     *
     * @param msg
     * @return
     */
    public static <T> Result<T> OkMsg(String msg) {
        return OkMsg(msg, null);
    }

    /**
     * OK
     *
     * @param resource
     * @return
     */
    public static <T> Result<T> OkResource(String resource) {
        return OkMsgResource(null, resource);
    }
    /**
     * OK
     *
     * @param msg
     * @return
     */
    public static <T> Result<T> OkMsgResource(String msg, String resource) {
        return OkMsg(msg, null, resource);
    }

    /**
     * OK
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Result<T> OkMsg(String msg, T data) {
        return OkMsg(msg, data, null);
    }

    /**
     * OK
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Result<T> OkMsg(String msg, T data, String resource) {
        return new Result<>(true, "200", msg, data, resource);
    }

    /**
     * OK
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> Ok(T data) {
        return OkDataResource(data, null);
    }

    /**
     * OK
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> OkDataResource(T data, String resource) {
        return OkMsg(null, data, resource);
    }





    /**
     * Error
     *
     * @param errorCode 指定错误码
     * @param msg
     * @return
     */
    public static <T> Result<T> ErrorCode(String errorCode, String msg, T data, String resource) {
        return new Result<>(false, errorCode, msg, data, resource);
    }

    /**
     * Error
     *
     * @param errorCode 指定错误码
     * @param msg
     * @return
     */
    public static <T> Result<T> ErrorCode(String errorCode, String msg, T data) {
        return ErrorCode(errorCode, msg, data, null);
    }

    /**
     * Error
     *
     * @param msg
     * @return
     */
    public static Result<String> ErrorCode(Integer code, String msg) {
        return ErrorCode(Integer.toString(code), msg, null);
    }

    /**
     * Error
     *
     * @param msg
     * @return
     */
    public static Result ErrorCodeResource(String code, String msg, String resource) {
        return ErrorCode(code, msg, null, resource);
    }

    /**
     * Error
     *
     * @param msg
     * @return
     */
    public static <T> Result<T> ErrorMsg(String msg, T data, String resource) {
        return new Result<>(false, "500", msg, data, resource);
    }

    /**
     * Error
     *
     * @param msg
     * @return
     */
    public static <T> Result<T> ErrorMsg(String msg, T data) {
        return ErrorMsg(msg, data, null);
    }

    /**
     * Error
     *
     * @param msg
     * @return
     */
    public static <T> Result<T> ErrorMsg(String msg) {
        return ErrorMsg(msg, null);
    }

    /**
     * Error
     *
     * @return
     */
    public static <T> Result<T> Error(T data) {
        return ErrorMsg("错误", data, null);
    }
    
    /**
     * Error
     *
     * @param msg
     * @return
     */
    public static <T> Result<T> ErrorMsg(String msg,String resource) {
        return new Result<>(false, "500", msg, null,resource);
    }


    @Override
    public String toString() {
//        final String s = MoreObjects.toStringHelper(this)
//                .add("success", success)
//                .add("resource", resource)
//                .add("code", code)
//                .add("msg", msg)
//                .add("data", data)
//                .toString();
//
//        System.out.println("result = " + s);
        return new StringJoiner(", ", Result.class.getSimpleName() + "[", "]")
                .add("success=" + success)
                .add("resource='" + resource + "'")
                .add("code='" + code + "'")
                .add("msg='" + msg + "'")
                .add("data=" + data)
                .toString();
    }

}
