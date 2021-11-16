package org.lover.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理自定义的业务异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    public  ResultBody bizExceptionHandler(HttpServletRequest req,BizException e)
    {
        log.error("发生业务异常，原因是:{}",e.getErrorMsg());
        return  ResultBody.error(e.getErrorCode(),e.getErrorMsg());
    }


    @ExceptionHandler(value = NullPointerException.class)
    public ResultBody nullPointerExceptionHandler(HttpServletRequest req,NullPointerException e)
    {
        log.error("发生空指针异常，原因是{}",e);
        return ResultBody.error(CommonEnum.BODY_NOT_MATCH);
    }

    @ExceptionHandler(value = BindException.class)
    public ResultBody bindExceptionHandler(HttpServletRequest req,BindException e)
    {
        List<FieldError>allErrors=e.getFieldErrors();
        StringBuffer sb=new StringBuffer();
        for(FieldError errorMessage:allErrors)
        {
            sb.append(errorMessage.getField()).append(":").append(errorMessage.getDefaultMessage()).append(",");
        }
        log.error(sb.toString());
        return ResultBody.error(sb.toString());
    }

    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
/*    @ExceptionHandler(value = Exception.class)
    public ResultBody exceptionHandler(HttpServletRequest req,Exception e)
    {
        log.error("未知异常，原因是{}",e);
        return ResultBody.error(CommonEnum.INTERNAL_SERVER_ERROR);
    }*/
}
