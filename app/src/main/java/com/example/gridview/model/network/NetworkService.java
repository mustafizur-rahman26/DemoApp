package com.example.gridview.model.network;


import com.example.gridview.model.entity.ImageListResponse;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Mustafizur Rahman on 5/27/18.
 */
public class NetworkService {
    private final ApiRequestService apiRequestService;

    public NetworkService(ApiRequestService apiRequestService) {
        this.apiRequestService = apiRequestService;
    }

    public Subscription getImageList(final GetImageListCallback callback) {

        return apiRequestService.getImageList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends ImageListResponse>>() {
                    @Override
                    public Observable<? extends ImageListResponse> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<ImageListResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(ImageListResponse imageListResponse) {
                        //Log.i("Test" , "Inside onNext :: Response: " + imageListResponse.toString());
                        callback.onSuccess(imageListResponse);
                    }
                });
    }

    public interface GetImageListCallback {
        void onSuccess(ImageListResponse imageListResponse);

        void onError(NetworkError networkError);
    }
}
