package thontepu.naveen.baseproject.Retrofit.Api.SampleGetApi;

import io.reactivex.Observable;
import thontepu.naveen.baseproject.Retrofit.BaseProjectApi;
import thontepu.naveen.baseproject.Retrofit.RxGenericController.GenericControllerRx;
import thontepu.naveen.baseproject.Retrofit.RxGenericController.RetrofitResponseObserver;

/**
 * Created by mac on 4/22/17
 */

public class SampleGetApiRxController extends GenericControllerRx<SampleGetResponse> {

    public SampleGetApiRxController(RetrofitResponseObserver<SampleGetResponse> observer) {
        super(observer);
    }

    @Override
    protected Observable<SampleGetResponse> makeApiCall(BaseProjectApi baseProjectApi) {
        return null;
    }
}
