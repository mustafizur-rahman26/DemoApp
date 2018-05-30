package com.example.gridview.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

public class ImageListResponse {

    @SerializedName("images")
    private List<ImageListData> data = new ArrayList<ImageListData>();


    /**
     *
     * @return
     * The data
     */
    public List<ImageListData> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(List<ImageListData> data) {
        this.data = data;
    }

    /**
     *
     * @return
     * The message
     */

    @Override
    public String toString() {
        return "ImageListResponse{" +
                "data=" + data +
                '}';
    }
}