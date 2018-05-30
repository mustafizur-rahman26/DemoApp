package com.example.gridview.list;

import com.example.gridview.model.entity.ImageListResponse;

/**
 * Created by Mustafizur Rahman on 6/25/16.
 */
public interface ListView {
    void showLoading();

    void hideLoading();

    void onFailure(String appErrorMessage);

    void getImageListSuccess(ImageListResponse imageListResponse);

}
