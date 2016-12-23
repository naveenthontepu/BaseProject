package thontepu.naveen.baseproject.Retrofit.Api.SampleGetApi;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mac on 12/22/16
 */

public class SampleGetResponse {
    @SerializedName("response")
    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
