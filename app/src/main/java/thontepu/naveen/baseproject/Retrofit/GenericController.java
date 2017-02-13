package thontepu.naveen.baseproject.Retrofit;

import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import thontepu.naveen.baseproject.Retrofit.Api.ApiResponseHandler;
import thontepu.naveen.baseproject.Retrofit.Api.ErrorResponse;
import thontepu.naveen.baseproject.Utilities.Constants;

/**
 * Created by mac on 2/13/17
 */

public abstract class GenericController<T> {

    // TODO: 2/13/17 have to write another generic controller using BUS(square OTTO)
    private final Retrofit retrofit;
    private final ApiResponseHandler<T> apiResponseHandler;
    private final BaseProjectApi baseProjectApi;

    public GenericController(ApiResponseHandler<T> api) {
        this(api, 20);
    }

    private GenericController(ApiResponseHandler<T> apiResponseHandler, int timeout) {
        this.apiResponseHandler = apiResponseHandler;
        RetrofitUtil retrofitUtil = new RetrofitUtil();
        retrofit = retrofitUtil.retrofitBuilder(timeout);
        baseProjectApi = retrofit.create(BaseProjectApi.class);
    }

    public void apiCall() {
        Call<T> apiCall = makeApiCall(baseProjectApi);
        apiCall.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                processResponse(response);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                processResponse(null);
            }
        });

    }

    abstract Call<T> makeApiCall(BaseProjectApi baseProjectApi);

    private void processResponse(Response<T> response) {
        if (response != null && response.isSuccessful()) {
            apiResponseHandler.handleResponse(response.body(), null);
        } else {
            ErrorResponse errorResponse = null;
            Converter<ResponseBody, ErrorResponse> converter = retrofit.responseBodyConverter(ErrorResponse.class, new Annotation[0]);
            try {
                if (response != null) {
                    errorResponse = converter.convert(response.errorBody());
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                e.printStackTrace();
                errorResponse = new ErrorResponse();
                if (response != null) {
                    errorResponse.setCode(response.code());
                    errorResponse.setMessage(response.message());
                } else {
                    errorResponse.setMessage("Unable to reach our servers.");
                    errorResponse.setCode(5000);
                }
                errorResponse.setStatus(Constants.ApiStatusAndMessage.ERROR);
            } finally {
                apiResponseHandler.handleResponse(null, errorResponse);
            }
        }
    }
}