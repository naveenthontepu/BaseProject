package thontepu.naveen.baseproject.Retrofit.Api.SampleGetApi;

import retrofit2.Call;
import thontepu.naveen.baseproject.Retrofit.Api.ApiResponseHandler;
import thontepu.naveen.baseproject.Retrofit.BaseProjectApi;
import thontepu.naveen.baseproject.Retrofit.GenericController;

/**
 * Created by mac on 3/30/17
 */

public class SampleGetApiController extends GenericController<SampleGetResponse> {
    public SampleGetApiController(ApiResponseHandler<SampleGetResponse> apiResponseHandler) {
        super(apiResponseHandler);
    }

    @Override
    public Call<SampleGetResponse> makeApiCall(BaseProjectApi baseProjectApi) {
        return baseProjectApi.sampleGetCall();
    }
}
