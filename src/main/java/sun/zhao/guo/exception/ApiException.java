package sun.zhao.guo.exception;

public class ApiException extends RuntimeException {
    ResultStat stat;

    public ResultStat getStat() {
        return stat;
    }

    public void setStat(ResultStat stat) {
        this.stat = stat;
    }

    protected ApiException(ResultStat stat) {
        this.stat = stat;
    }

    protected ApiException(ResultStat stat, String message) {
        super(message);
        this.stat = stat;
    }

    private ApiException(ResultStat stat, Throwable cause) {
        super(cause);
        this.stat = stat;
    }

    private ApiException(ResultStat stat, String msg, Throwable cause) {
        super(msg, cause);
        this.stat = stat;
    }

    protected ApiException() {
    }

    /**
     * use this function when you need to return to user and know how to handle this exception
     * <p>
     * only message will be logged
     *
     * @param stat
     * @param cause
     * @return
     */
    public static ApiException wrapKnownException(ResultStat stat, Throwable cause) {
        return new ApiException(stat, cause.getMessage());
    }

    /**
     * use this function when you need to return to user and do not know how to handle the exception
     * <p>
     * this exception will be logged stack trace unified.
     *
     * @param cause
     * @return
     */
    public static ApiException wrapUnknownException(Throwable cause) {
        return new ApiException(ResultStat.SERVER_INTERNAL_ERROR, cause);
    }

    /**
     * use this function when you need to return a stat and message to user
     *
     * @param stat
     * @param message
     * @return
     */
    public static ApiException wrapMessage(ResultStat stat, String message) {
        return new ApiException(stat, message);
    }

    public static ApiException wrapResultStat(ResultStat stat) {
        return new ApiException(stat);
    }
}