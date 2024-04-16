package sun.zhao.guo.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import sun.zhao.guo.common.Result;
import sun.zhao.guo.component.IMyLog;
import sun.zhao.guo.exception.ApiException;
import sun.zhao.guo.exception.HttpResponseTemp;
import sun.zhao.guo.exception.ResultStat;


/**
 * 操作名称：ApiController  </br>
 * 操作标题：SpringBoot其他Controller抽象基类Api </br>
 * 操作包作用：SpringBoot其他Controller抽象基类Api，提供公用方式 </br>
 * 源程序文件名：ApiController</br>
 * 编者：中科星图</br>
 * 时间:2019/07/04</br>
 */
public abstract class ApiController implements IMyLog {

    private Result<String> responseToEntity(HttpResponseTemp<?> response) {
        String body = response.getResultMsg();
        return Result.npeErrorResult(body);
    }

    @ExceptionHandler(ApiException.class)
    public Result<String> deployExceptionHandler(ApiException e) {
        StringBuilder msg = new StringBuilder();
        if (e.getCause() != null) {
            Throwable t = e.getCause();
            getLogger().error("unexpected exception happened:" + t.getMessage(), e);
            msg.append(t.getClass().getSimpleName());
            msg.append(":");
        }
        msg.append(e.getMessage());
        return responseToEntity(e.getStat().wrap(null, msg.toString()));
    }

    @ExceptionHandler(Exception.class)
    public Result<String> defaultExceptionHandler(Exception e) {
        getLogger().error("unexpected exception happened:" + e.getMessage(), e);
        return responseToEntity(ResultStat.SERVER_INTERNAL_ERROR.wrap(null, e.getMessage()));
    }

}
