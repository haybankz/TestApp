
package com.example.testapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Genre {

    @SerializedName("id")
    @Expose
    private Long mId;

    @SerializedName("name")
    @Expose
    private String mName;

    public Long getId() {
        return mId;
    }


    public String getName() {
        return mName;
    }

}
