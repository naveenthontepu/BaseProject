package thontepu.naveen.baseproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;
import retrofit2.Call;
import thontepu.naveen.baseproject.R;
import thontepu.naveen.baseproject.Retrofit.Api.ApiResponseHandler;
import thontepu.naveen.baseproject.Retrofit.Api.ErrorResponse;
import thontepu.naveen.baseproject.Retrofit.Api.SampleGetApi.SampleGetResponse;
import thontepu.naveen.baseproject.Retrofit.BaseProjectApi;
import thontepu.naveen.baseproject.Retrofit.GenericController;
import thontepu.naveen.baseproject.Utilities.Constants;
import thontepu.naveen.baseproject.Utilities.SensorUtilities;
import thontepu.naveen.baseproject.Utilities.Utilities;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_splash_screen);
    }

    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }

    public void apiCall(View view) {
        Utilities.printLog("--------------------------------------------------------------------------------");
        GenericController<SampleGetResponse> sampleGetApi = new GenericController<SampleGetResponse>(new ApiResponseHandler<SampleGetResponse>() {
            @Override
            public void handleResponse(SampleGetResponse sampleGetResponse, ErrorResponse errorResponse) {
                Utilities.printLog(Constants.Tags.RETROFIT_TAG, "the sample response = " + sampleGetResponse);
                Utilities.printLog(Constants.Tags.RETROFIT_TAG, "the error response = " + errorResponse);
            }
        }) {
            @Override
            public Call<SampleGetResponse> makeApiCall(BaseProjectApi baseProjectApi) {
                return baseProjectApi.sampleGetCall();
            }
        };
        sampleGetApi.apiCall();
    }

    public void startVibration(View view) {
        SensorUtilities.triggerVibration(getApplicationContext(),25000);
        Intent mainIntent = new Intent(this,MainActivity.class);
        startActivity(mainIntent);
    }
}
