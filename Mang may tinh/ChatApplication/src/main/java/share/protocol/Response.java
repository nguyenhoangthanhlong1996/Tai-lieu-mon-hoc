package share.protocol;

import java.io.Serializable;

public class Response implements Serializable {

    private ResponseType responseType;
    private boolean success;
    private Object data;

    public Response(ResponseType responseType, boolean success, Object data) {
        this.responseType = responseType;
        //cờ hiệu response
        this.success = success;
        this.data = data;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "responseType=" + responseType +
                ", success=" + success +
                ", data=" + data +
                '}';
    }
}
