package org.lover.exception;


import com.alibaba.fastjson.JSONObject;

public class ResultBody {

    private  String code;

    private String message;

    private Object result;

    public ResultBody() {
    }

    public  ResultBody(BaseErrorInfo errorInfo)
    {
        code=errorInfo.getResultCode();
        message=errorInfo.getResultMsg();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }


    /**
     * 成功
     *
     * @return
     */
    public static ResultBody success() {
        return success(null);
    }

    /**
     * 成功
     * @param data
     * @return
     */

    public  static  ResultBody success(Object data)
    {
        ResultBody body=new ResultBody();
        body.setCode(CommonEnum.SUCCESS.getResultCode());
        body.setMessage(CommonEnum.SUCCESS.getResultMsg());
        body.setResult(data);
        return body;
    }

    /**
     * 失败
     */
    public  static  ResultBody error(BaseErrorInfo errorInfo)
    {
        ResultBody rb=new ResultBody();
        rb.setCode(errorInfo.getResultCode());
        rb.setMessage(errorInfo.getResultMsg());
        rb.setResult(null);
        return rb;
    }



    /**
     * 失败
     */
    public static ResultBody error(String code, String message)
    {
        ResultBody rb=new ResultBody();
        rb.setCode(code);
        rb.setMessage(message);
        rb.setResult(null);
        return rb;

    }


    /**
     * 失败
     */
    public static ResultBody error( String message)
    {
        ResultBody rb=new ResultBody();
        rb.setCode("-1");
        rb.setMessage(message);
        rb.setResult(null);
        return rb;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
