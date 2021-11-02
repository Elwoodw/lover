package org.lover.exception;

public class BizException extends RuntimeException {
    protected  String errorCode;
    protected  String errorMsg;

    public BizException() {
        super();
    }

    public BizException(BaseErrorInfo errorInfo)
    {
        super(errorInfo.getResultCode());
        errorCode=errorInfo.getResultCode();
        errorMsg=errorInfo.getResultMsg();
    }

    public BizException(BaseErrorInfo errorInfo,Throwable cause)
    {
        super(errorInfo.getResultCode(),cause);
        errorCode=errorInfo.getResultCode();
        errorMsg=errorInfo.getResultMsg();
    }

    public BizException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public BizException(String errorCode, String errorMsg, Throwable cause)
    {
        super(errorCode,cause);
        this.errorCode=errorCode;
        this.errorMsg=errorMsg;
    }



    public BizException(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }



    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
