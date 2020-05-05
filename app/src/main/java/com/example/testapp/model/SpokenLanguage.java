
package com.example.testapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpokenLanguage {

    @Expose
    @SerializedName("iso_639_1")
    private String mIso6391;

    @Expose
    @SerializedName("name")
    private String mName;

    public String getIso6391() {
        return mIso6391;
    }


    public String getName() {
        return mName;
    }


}
