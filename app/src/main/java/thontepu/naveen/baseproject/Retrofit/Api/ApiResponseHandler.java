package thontepu.naveen.baseproject.Retrofit.Api;

/**
 * Created by mac on 11/5/16
 */

public interface ApiResponseHandler<K> {
    void handleResponse(K k, ErrorResponse errorResponse);
}
