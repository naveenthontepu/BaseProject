package thontepu.naveen.baseproject.Retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import thontepu.naveen.baseproject.BuildConfig;

/**
 * Created by naveen thontepu on 08-03-2016
 */
public class RetrofitFactory {
    private final Retrofit retrofit;
    private final AuthInterceptor authInterceptor;
    private final static RetrofitFactory apiFactory = new RetrofitFactory();
    private final BaseProjectApi baseProjectApi;
    private final OkHttpClient okHttpClient;

    private RetrofitFactory() {
        authInterceptor = new AuthInterceptor();
        okHttpClient = getOkhttpClient(20);
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                // TODO: 2/13/17 Change the testing url and production url and you are good to go
                .baseUrl(BuildConfig.testing ? RetrofitConstants.UrlContstants.testingUrl : "https://productionUrl")
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
//