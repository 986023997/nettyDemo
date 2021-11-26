package org.ciapge.bean;

/**
 * @author 朱林
 * @description
 * @date 2021/11/23 17:06
 */
public class Request {
    private String requestId;
    private Object param;


    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }
}
