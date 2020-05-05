
package com.example.testapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Movie implements Serializable {

    @SerializedName("adult")
    @Expose
    private Boolean mAdult;

    @SerializedName("backdrop_path")
    @Expose
    private String mBackdropPath;

    @SerializedName("genre_ids")
    @Expose
    private List<Long> mGenreIds;

    @SerializedName("id")
    @Expose
    private Long mId;

    @SerializedName("original_language")
    @Expose
    private String mOriginalLanguage;

    @SerializedName("original_title")
    @Expose
    private String mOriginalTitle;

    @SerializedName("overview")
    @Expose
    private String mOverview;

    @SerializedName("popularity")
    @Expose
    private Double mPopularity;

    @SerializedName("poster_path")
    @Expose
    private String mPosterPath;

    @SerializedName("release_date")
    @Expose
    private String mReleaseDate;

    @SerializedName("title")
    @Expose
    private String mTitle;

    @SerializedName("video")
    @Expose
    private Boolean mVideo;

    @SerializedName("vote_average")
    @Expose
    private Double mVoteAverage;

    @SerializedName("vote_count")
    @Expose
    private Long mVoteCount;

    public Boolean getAdult() {
        return mAdult;
    }


    public String getBackdropPath() {
        return mBackdropPath;
    }


    public List<Long> getGenreIds() {
        return mGenreIds;
    }

    public Long getId() {
        return mId;
    }

    public String getOriginalLanguage() {
        return mOriginalLanguage;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public String getOverview() {
        return mOverview;
    }

    public Double getPopularity() {
        return mPopularity;
    }


    public String getPosterPath() {
        return mPosterPath;
    }


    public String getReleaseDate() {
        return mReleaseDate;
    }


    public String getTitle() {
        return mTitle;
    }


    public Boolean getVideo() {
        return mVideo;
    }


    public Double getVoteAverage() {
        return mVoteAverage;
    }


    public Long getVoteCount() {
        return mVoteCount;
    }

    public void setmAdult(Boolean mAdult) {
        this.mAdult = mAdult;
    }

    public void setBackdropPath(String mBackdropPath) {
        this.mBackdropPath = mBackdropPath;
    }

    public void setGenreIds(List<Long> mGenreIds) {
        this.mGenreIds = mGenreIds;
    }

    public void setId(Long mId) {
        this.mId = mId;
    }

    public void setOriginalLanguage(String mOriginalLanguage) {
        this.mOriginalLanguage = mOriginalLanguage;
    }

    public void setOriginalTitle(String mOriginalTitle) {
        this.mOriginalTitle = mOriginalTitle;
    }

    public void setOverview(String mOverview) {
        this.mOverview = mOverview;
    }

    public void setPopularity(Double mPopularity) {
        this.mPopularity = mPopularity;
    }

    public void setPosterPath(String mPosterPath) {
        this.mPosterPath = mPosterPath;
    }

    public void setReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setVideo(Boolean mVideo) {
        this.mVideo = mVideo;
    }

    public void setVoteAverage(Double mVoteAverage) {
        this.mVoteAverage = mVoteAverage;
    }

    public void setVoteCount(Long mVoteCount) {
        this.mVoteCount = mVoteCount;
    }
}
