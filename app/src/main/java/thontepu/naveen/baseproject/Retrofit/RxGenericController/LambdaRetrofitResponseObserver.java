package thontepu.naveen.baseproject.Retrofit.RxGenericController;

import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.disposables.DisposableHelper;
import thontepu.naveen.baseproject.Retrofit.Api.ErrorResponse;

/**
 * Created by mac on 3/21/17
 */

class LambdaRetrofitResponseObserver<T> extends RetrofitResponseObserver<T> {
    private final Consumer<? super T> onNext;
    private final RetrofitErrorConsumer onError;
    private final Action onComplete;
    private final Consumer<? super Disposable> onSubscribe;

    LambdaRetrofitResponseObserver(Consumer<? super T> onNext, RetrofitErrorConsumer onError, Action onComplete, Consumer<? super Disposable> onSubscribe) {
        this.onNext = onNext;
        this.onError = onError;
        this.onComplete = onComplete;
        this.onSubscribe = onSubscribe;
    }

    @Override
    public void onError(ErrorResponse errorResponse) {
        if (!isDisposed()) {
            dispose();
            onError.accept(errorResponse);
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (DisposableHelper.setOnce(this, d)) {
            try {
                onSubscribe.accept(this);
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                onError(ex);
            }
        }
    }

    @Override
    public void onNext(T value) {
        if (!isDisposed()) {
            try {
                onNext.accept(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onComplete() {
        if (!isDisposed()) {
            dispose();
            try {
                onComplete.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
