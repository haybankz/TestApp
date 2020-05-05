
package com.example.testapp.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UpcomingResponse {

    @Expose
    @SerializedName("page")
    private Long mPage;

    @Expose
    @SerializedName("results")
    private List<Movie> mMovies;

    @Expose
    @SerializedName("total_pages")
    private Long mTotalPages;

    @Expose
    @SerializedName("total_results")
    private Long mTotalResults;


    public Long getPage() {
        return mPage;
    }


    public List<Movie> getResults() {
        return mMovies;
    }


    public Long getTotalPages() {
        return mTotalPages;
    }


    public Long getTotalResults() {
        return mTotalResults;
    }


}
