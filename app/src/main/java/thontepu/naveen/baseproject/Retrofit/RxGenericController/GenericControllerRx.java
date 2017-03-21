package thontepu.naveen.baseproject.Retrofit.RxGenericController;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.SchedulerSupport;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.schedulers.Schedulers;
import thontepu.naveen.baseproject.Retrofit.Api.ErrorResponse;
import thontepu.naveen.baseproject.Retrofit.BaseProjectApi;

/**
 * Created by mac on 2/27/17
 */

public abstract class GenericControllerRx<T> {
    private RetrofitResponseObserver<T> observer;
    private Consumer<T> consumer;

    public GenericControllerRx(RetrofitResponseObserver<T> observer) {
        this.observer = observer;
    }

    public GenericControllerRx(final Consumer<T> consumer) {
        this.observer = subscribe(consumer, new RetrofitErrorConsumer() {
            @Override
            public void accept(ErrorResponse errorResponse) {

            }
        }, Functions.EMPTY_ACTION, Functions.emptyConsumer());
    }

    public GenericControllerRx(final Consumer<T> onNext, final RetrofitErrorConsumer onError) {
        this.observer = subscribe(onNext, onError, Functions.EMPTY_ACTION, Functions.emptyConsumer());
    }

    public GenericControllerRx(final Consumer<T> onNext, final RetrofitErrorConsumer onError, final Action onComplete) {
        this.observer = subscribe(onNext, onError, onComplete, Functions.emptyConsumer());
    }

    public GenericControllerRx(final Consumer<T> onNext, final RetrofitErrorConsumer onError, final Action onComplete, final Consumer<? super Disposable> onSubscribe) {
        this.observer = subscribe(onNext, onError, onComplete, onSubscribe);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    private RetrofitResponseObserver<T> subscribe(Consumer<T> onNext, RetrofitErrorConsumer onError,
                                                  Action onComplete, Consumer<? super Disposable> onSubscribe) {
        ObjectHelper.requireNonNull(onNext, "onNext is null");
        ObjectHelper.requireNonNull(onError, "onError is null");
        ObjectHelper.requireNonNull(onComplete, "onComplete is null");
        ObjectHelper.requireNonNull(onSubscribe, "onSubscribe is null");

        LambdaRetrofitResponseObserver<T> ls = new LambdaRetrofitResponseObserver<>(onNext, onError, onComplete, onSubscribe);
        return ls;
    }

    public void apiCall() {
        Observable<T> apiCall = makeApiCall(RetrofitRxFactory.getInstance().getBaseProjectApi());
        apiCall.subscribe(new Consumer<T>() {
            @Override
            public void accept(T t) throws Exception {

            }
        });
        apiCall.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    protected abstract Observable<T> makeApiCall(BaseProjectApi baseProjectApi);
}
