package com.example.gridview.model.network;


import com.example.gridview.model.entity.ImageListResponse;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Mustafizur Rahman on 5/27/18.
 */
public interface ApiRequestService {
    @GET("api/v1/images/search?query=tree")
    //@GET("3/gallery/random/random/3")
    Observable<ImageListResponse> getImageList();
}
