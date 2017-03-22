package thontepu.naveen.baseproject.Retrofit.RxGenericController;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import thontepu.naveen.baseproject.BuildConfig;
import thontepu.naveen.baseproject.Retrofit.AuthInterceptor;
import thontepu.naveen.baseproject.Retrofit.BaseProjectApi;

/**
 * Created by mac on 3/21/17
 */

class RetrofitRxFactory {
    private final Retrofit retrofit;
    private final AuthInterceptor authInterceptor;
    private final static RetrofitRxFactory apiFactory = new RetrofitRxFactory();
    private final BaseProjectApi baseProjectApi;
    private final OkHttpClient okHttpClient;

    private RetrofitRxFactory() {
        authInterceptor = new AuthInterceptor();
        okHttpClient = getOkhttpClient(20);
        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                // TODO: 2/13/17 Change the testing url and production url and you are good to go
                .baseUrl(BuildConfig.testing ? "http://testingURl" : "https://productionUrl")
                .client(okHttpClient)
                .build();
        baseProjectApi = retrofit.create(BaseProjectApi.class);
    }

    public static RetrofitRxFactory getInstance() {
        return apiFactory;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public BaseProjectApi getBaseProjectApi() {
        return baseProjectApi;
    }

    //Would be used for testing
    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    private OkHttpClient getOkhttpClient(Integer timeout) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .addInterceptor(authInterceptor);
        if (BuildConfig.DEBUG) {
            //For Logging
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }
        return builder.build();
    }

}
