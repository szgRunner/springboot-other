package sun.zhao.guo.exception;

import java.io.Serializable;

//Domeos返回值统一接口,来自Domeos
public class HttpResponseTemp<T> implements Serializable {

	private T result;
	private int resultCode;
	private String resultMsg = "";

	public HttpResponseTemp() {
    }


	public HttpResponseTemp(T data, ResultStat code, String msg) {
        this.result = data;
        this.resultCode = code.getCode();
        this.resultMsg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
