package thontepu.naveen.baseproject.Retrofit.RxGenericController;

import java.lang.annotation.Annotation;

import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.adapter.rxjava.HttpException;
import thontepu.naveen.baseproject.Retrofit.Api.ErrorResponse;
import thontepu.naveen.baseproject.Utilities.Constants;

/**
 * Created by mac on 3/21/17
 */

public abstract class RetrofitErrorConsumer implements Consumer<Throwable> {
    @Override
    public void accept(Throwable e) throws Exception {
        ErrorResponse errorResponse = new ErrorResponse();
        try {
            if (e instanceof HttpException) {
                HttpException exception = (HttpException) e;
                Converter<ResponseBody, ErrorResponse> converter = RetrofitRxFactory.getInstance().getRetrofit().responseBodyConverter(ErrorResponse.class, new Annotation[0]);
                if (exception.response() != null && exception.response().errorBody() != null) {
                    errorResponse = converter.convert(exception.response().errorBody());
                } else if (exception.response() != null) {
                    errorResponse.setCode(exception.response().code());
                    errorResponse.setMessage(exception.response().message());
                    errorResponse.setStatus(Constants.ApiStatusAndMessage.ERROR);
                } else {
                    throw new Exception();
                }
            } else {
                throw new Exception();
            }
        } catch (Exception ignored) {
            errorResponse.setMessage("Unable to reach our servers.");
            errorResponse.setCode(5000);
            errorResponse.setStatus(Constants.ApiStatusAndMessage.ERROR);
        } finally {
            accept(errorResponse);
        }
    }

    public abstract void accept(ErrorResponse errorResponse);
}
