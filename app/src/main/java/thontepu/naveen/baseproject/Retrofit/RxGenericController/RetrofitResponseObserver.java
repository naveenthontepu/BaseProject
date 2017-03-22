package thontepu.naveen.baseproject.Retrofit.RxGenericController;

import java.lang.annotation.Annotation;
import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.HttpException;
import thontepu.naveen.baseproject.Retrofit.Api.ErrorResponse;
import thontepu.naveen.baseproject.Utilities.Constants;

/**
 * Created by mac on 3/21/17
 */

public abstract class RetrofitResponseObserver<T> extends AtomicReference<Disposable> implements Observer<T>,Disposable {

    @Override
    public void onError(Throwable e) {
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
            onError(errorResponse);
        }
    }

    @Override
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    @Override
    public boolean isDisposed() {
        return get() == DisposableHelper.DISPOSED;
    }

    public abstract void onError(ErrorResponse errorResponse);
}
