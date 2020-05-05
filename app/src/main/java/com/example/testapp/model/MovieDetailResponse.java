
package com.example.testapp.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MovieDetailResponse {

    @Expose
    @SerializedName("adult")
    private Boolean mAdult;

    @Expose
    @SerializedName("backdrop_path")
    private String mBackdropPath;

    @Expose
    @SerializedName("belongs_to_collection")
    private Object mBelongsToCollection;

    @Expose
    @SerializedName("budget")
    private Long mBudget;

    @Expose
    @SerializedName("genres")
    private List<Genre> mGenres;

    @Expose
    @SerializedName("homepage")
    private String mHomepage;

    @Expose
    @SerializedName("id")
    private Long mId;

    @Expose
    @SerializedName("imdb_id")
    private String mImdbId;

    @Expose
    @SerializedName("original_language")
    private String mOriginalLanguage;

    @Expose
    @SerializedName("original_title")
    private String mOriginalTitle;

    @Expose
    @SerializedName("overview")
    private String mOverview;

    @Expose
    @SerializedName("popularity")
    private Double mPopularity;

    @Expose
    @SerializedName("poster_path")
    private String mPosterPath;

    @Expose
    @SerializedName("production_companies")
    private List<ProductionCompany> mProductionCompanies;

    @Expose
    @SerializedName("production_countries")
    private List<ProductionCountry> mProductionCountries;

    @Expose
    @SerializedName("release_date")
    private String mReleaseDate;

    @Expose
    @SerializedName("revenue")
    private Long mRevenue;

    @Expose
    @SerializedName("runtime")
    private Long mRuntime;

    @Expose
    @SerializedName("spoken_languages")
    private List<SpokenLanguage> mSpokenLanguages;

    @Expose
    @SerializedName("status")
    private String mStatus;

    @Expose
    @SerializedName("tagline")
    private String mTagline;

    @Expose
    @SerializedName("title")
    private String mTitle;

    @Expose
    @SerializedName("video")
    private Boolean mVideo;

    @Expose
    @SerializedName("vote_average")
    private Double mVoteAverage;

    @Expose
    @SerializedName("vote_count")
    private Long mVoteCount;

    public Boolean getAdult() {
        return mAdult;
    }


    public String getBackdropPath() {
        return mBackdropPath;
    }

    public Object getBelongsToCollection() {
        return mBelongsToCollection;
    }



    public Long getBudget() {
        return mBudget;
    }


    public List<Genre> getGenres() {
        return mGenres;
    }


    public String getHomepage() {
        return mHomepage;
    }


    public Long getId() {
        return mId;
    }


    public String getImdbId() {
        return mImdbId;
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


    public List<ProductionCompany> getProductionCompanies() {
        return mProductionCompanies;
    }



    public List<ProductionCountry> getProductionCountries() {
        return mProductionCountries;
    }



    public String getReleaseDate() {
        return mReleaseDate;
    }


    public Long getRevenue() {
        return mRevenue;
    }


    public Long getRuntime() {
        return mRuntime;
    }


    public List<SpokenLanguage> getSpokenLanguages() {
        return mSpokenLanguages;
    }



    public String getStatus() {
        return mStatus;
    }


    public String getTagline() {
        return mTagline;
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


}
