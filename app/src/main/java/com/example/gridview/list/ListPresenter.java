package com.example.gridview.list;

import android.util.Log;

import com.example.gridview.model.entity.ImageListData;
import com.example.gridview.model.entity.ImageListResponse;
import com.example.gridview.model.network.NetworkError;
import com.example.gridview.model.network.NetworkService;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Mustafizur Rahman on 5/26/18.
 */
public class ListPresenter {
    private final NetworkService networkService;
    private final ListView view;
    private CompositeSubscription subscriptions;

    public ListPresenter(NetworkService networkService, ListView view) {
        this.networkService = networkService;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getImageList() {
        view.showLoading();

        Subscription subscription = networkService.getImageList(new NetworkService.GetImageListCallback() {
            @Override
            public void onSuccess(ImageListResponse imageListResponse) {
                view.hideLoading();
                view.getImageListSuccess(imageListResponse);
            }

            @Override
            public void onError(NetworkError networkError) {
                view.hideLoading();
                view.onFailure(networkError.getAppErrorMessage());
            }
        });

        subscriptions.add(subscription);
    }

    public void onStop() {
        if(subscriptions != null) {
            subscriptions.unsubscribe();
        }
    }
}
