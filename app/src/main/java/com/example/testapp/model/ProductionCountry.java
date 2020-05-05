
package com.example.testapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ProductionCountry {

    @Expose
    @SerializedName("iso_3166_1")
    private String mIso31661;

    @Expose
    @SerializedName("name")
    private String mName;

    public String getIso31661() {
        return mIso31661;
    }


    public String getName() {
        return mName;
    }


}
