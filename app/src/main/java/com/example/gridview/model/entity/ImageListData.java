package com.example.gridview.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageListData implements Parcelable{

    @SerializedName("id")
    private String id;

    @SerializedName("site")
    private String site;

    @SerializedName("copyright")
    private String copyright;

    @SerializedName("url")
    private String url;

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The name
     */
    public String getSite() {
        return site;
    }

    /**
     *
     * @param site
     * The name
     */
    public void setSite(String site) {
        this.site = site;
    }

    /**
     *
     * @return
     * The copyright
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     *
     * @param copyright
     * The copyright
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ImageListData{" +
                "id='" + id + '\'' +
                ", name='" + site + '\'' +
                ", copyright='" + copyright + '\'' +
                ", url='" + url + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.site);
        dest.writeString(this.copyright);
        dest.writeString(this.url);
    }

    public ImageListData() {
    }

    protected ImageListData(Parcel in) {
        this.id = in.readString();
        this.site = in.readString();
        this.copyright = in.readString();
        this.url = in.readString();
    }

    public static final Creator<ImageListData> CREATOR = new Creator<ImageListData>() {
        @Override
        public ImageListData createFromParcel(Parcel source) {
            return new ImageListData(source);
        }

        @Override
        public ImageListData[] newArray(int size) {
            return new ImageListData[size];
        }
    };
}