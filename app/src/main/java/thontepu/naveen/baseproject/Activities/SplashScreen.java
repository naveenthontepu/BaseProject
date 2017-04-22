package thontepu.naveen.baseproject.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.crashlytics.android.Crashlytics;

import javax.inject.Inject;

import io.fabric.sdk.android.Fabric;
import retrofit2.Call;
import thontepu.naveen.baseproject.BaseProject;
import thontepu.naveen.baseproject.R;
import thontepu.naveen.baseproject.Retrofit.Api.ApiResponseHandler;
import thontepu.naveen.baseproject.Retrofit.Api.ErrorResponse;
import thontepu.naveen.baseproject.Retrofit.Api.SampleGetApi.SampleGetApiController;
import thontepu.naveen.baseproject.Retrofit.Api.SampleGetApi.SampleGetResponse;
import thontepu.naveen.baseproject.Retrofit.BaseProjectApi;
import thontepu.naveen.baseproject.Retrofit.GenericController;
import thontepu.naveen.baseproject.Utilities.Constants;
import thontepu.naveen.baseproject.Utilities.SensorUtilities;
import thontepu.naveen.baseproject.Utilities.Utilities;

public class SplashScreen extends AppCompatActivity {

    @Inject
    BaseProject baseProject;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_splash_screen);
        ((BaseProject)getApplication()).getBaseProjectComponent().inject(this);
        if (baseProject!=null) {
            Utilities.printLog(baseProject.getString());
        }else {
            Utilities.printLog("injection did not happen");
        }
    }

    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }

    public void apiCall(View view) {
        Utilities.printLog("--------------------------------------------------------------------------------");
        progressDialog = Utilities.getProgressDialog(this,R.string.app_name);
        Utilities.showProgressDialog(progressDialog);
        SampleGetApiController sampleGetApiController = new SampleGetApiController((sampleGetResponse, errorResponse) -> {
            Utilities.dismissDialog(progressDialog);
            Utilities.printLog(Constants.Tags.RETROFIT_TAG, "the sample response = " + sampleGetResponse);
            Utilities.printLog(Constants.Tags.RETROFIT_TAG, "the error response = " + errorResponse);
        });
        sampleGetApiController.apiCall();
//        GenericController<SampleGetResponse> sampleGetApi = new GenericController<SampleGetResponse>(new ApiResponseHandler<SampleGetResponse>() {
//            @Override
//            public void handleResponse(SampleGetResponse sampleGetResponse, ErrorResponse errorResponse) {
//                Utilities.printLog(Constants.Tags.RETROFIT_TAG, "the sample response = " + sampleGetResponse);
//                Utilities.printLog(Constants.Tags.RETROFIT_TAG, "the error response = " + errorResponse);
//            }
//        }) {
//            @Override
//            public Call<SampleGetResponse> makeApiCall(BaseProjectApi baseProjectApi) {
//                return baseProjectApi.sampleGetCall();
//            }
//        };
//        sampleGetApi.apiCall();
    }

    public void startVibration(View view) {
        SensorUtilities.triggerVibration(getApplicationContext(),25000);
        Intent mainIntent = new Intent(this,MainActivity.class);
        startActivity(mainIntent);
    }
}
