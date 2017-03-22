package thontepu.naveen.baseproject.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import thontepu.naveen.baseproject.Retrofit.Api.SampleGetApi.SampleGetResponse;

/**
 * Created by mac on 12/22/16
 */

public interface BaseProjectApi {
    @GET("http://stackoverflow.com/questions/35419062/how-to-stop-and-resume-observable-interval-emiting-ticks")
    Call<SampleGetResponse> sampleGetCall();
}
