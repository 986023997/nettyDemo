package bean;

/**
 * @author 朱林
 * @description
 * @date 2021/11/23 17:13
 */
public class Response {
    private String responseId;
    private String requestId;
    private String result;

    public Response(String responseId, String requestId, String result) {
        this.responseId = responseId;
        this.requestId = requestId;
        this.result = result;
    }

    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
