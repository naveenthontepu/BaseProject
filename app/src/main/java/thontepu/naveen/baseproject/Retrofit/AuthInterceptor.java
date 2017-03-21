package thontepu.naveen.baseproject.Retrofit;

import android.util.Base64;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import thontepu.naveen.baseproject.Utilities.Constants;
import thontepu.naveen.baseproject.Utilities.Utilities;

/**
 * Created by mac on 11/5/16
 */

public class AuthInterceptor implements Interceptor{
    private String authKey;

    void setAuthKey(String userId, String password) {
        String credentials = userId + ":" + password;
        authKey = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request newRequest = getNewRequest(chain);
        if (authKey !=null && !authKey.isEmpty()) {
            // TODO: 12/22/16 Add common headers for all the apis
            Headers sessionHeader = newRequest.headers().newBuilder()
                    .add(Constants.HeadersConstants.AUTH_TOKEN, authKey)
                    .build();
            newRequest = newRequest.newBuilder().headers(sessionHeader).build();
        }
        Utilities.printLog(Constants.Tags.RETROFIT_TAG,"the headers for everything = " + newRequest.headers().toString());
        Response response = chain.proceed(newRequest);
        Utilities.printLog(Constants.Tags.RETROFIT_TAG,"the response in interceptor = " + response.toString());
        return response;
    }


    private Request getNewRequest(Chain chain) {
        return chain.request()
                .newBuilder()
                .build();
    }

}
