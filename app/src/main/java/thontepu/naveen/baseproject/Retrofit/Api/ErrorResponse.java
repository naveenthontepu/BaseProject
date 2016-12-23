package thontepu.naveen.baseproject.Retrofit.Api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mac on 8/19/16
 */
public class ErrorResponse {
    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("code")
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "code = "+code+ "  message == "+message+" status == "+status;
    }
}
