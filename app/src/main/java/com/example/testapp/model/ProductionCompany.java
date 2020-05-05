
package com.example.testapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ProductionCompany {

    @Expose
    @SerializedName("id")
    private Long mId;

    @Expose
    @SerializedName("logo_path")
    private String mLogoPath;

    @Expose
    @SerializedName("name")
    private String mName;

    @Expose
    @SerializedName("origin_country")
    private String mOriginCountry;

    public Long getId() {
        return mId;
    }


    public String getLogoPath() {
        return mLogoPath;
    }


    public String getName() {
        return mName;
    }


    public String getOriginCountry() {
        return mOriginCountry;
    }


}
