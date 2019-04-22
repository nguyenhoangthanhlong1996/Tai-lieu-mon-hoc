package share.protocol;

import java.io.Serializable;

public class Response implements Serializable {

    private RequestType requestType;
    private Object data;

    public Response(RequestType requestType, Object data) {
        this.requestType = requestType;
        this.data = data;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestType=" + requestType +
                ", data=" + data +
                '}';
    }
}
