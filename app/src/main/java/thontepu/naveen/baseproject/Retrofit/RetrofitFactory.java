package thontepu.naveen.baseproject.Retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import thontepu.naveen.baseproject.BuildConfig;
import thontepu.naveen.baseproject.Utilities.Utilities;

/**
 * Created by naveen thontepu on 08-03-2016
 */
class RetrofitFactory {
    private final Retrofit retrofit;
    private final AuthInterceptor authInterceptor;
    private final static RetrofitFactory apiFactory = new RetrofitFactory();
    private final BaseProjectApi baseProjectApi;
    private final OkHttpClient okHttpClient;

    public RetrofitFactory() {
        authInterceptor = new AuthInterceptor();
        okHttpClient = getOkhttpClient(20);
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.testing ? "http://testingURl" : "https://productionUrl")
                .client(okHttpClient)
                .build();
        baseProjectApi = retrofit.create(BaseProjectApi.class);
    }

    public static RetrofitFactory getInstance() {
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

    Retrofit retrofitBuilder(Integer integer) {
        Utilities.printLog("all fields valid");
        // TODO: 2/13/17 Change the testing url and production url and you are good to go
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.testing ? "http://testingURl" : "https://productionUrl")
//                .client(getOkhttpClientProd(integer))
                .build();
    }

    Retrofit rxRetrofitBuilder(Integer integer){
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.testing ? "http://testingURl" : "https://productionUrl")
//                .client(getOkhttpClientProd(integer))
                .build();
    }

    //for production
    private OkHttpClient getOkhttpClient(Integer timeout) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .addInterceptor(new AuthInterceptor());
        if (BuildConfig.DEBUG) {
            //For Logging
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }
        return builder.build();
    }

}
//