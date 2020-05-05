package com.example.testapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favourite")
public class Favourite {


    @ColumnInfo(name = "movie_id")
    @PrimaryKey
    private long mMovieId;

    @ColumnInfo(name = "movie_title")
    private String mMovieTitle;

    @ColumnInfo(name = "movie_poster_path")
    private String mMoviePosterPath;

    @ColumnInfo(name = "movie_overview")
    private String mOverview;

    public Favourite(long mMovieId, String mMovieTitle, String mMoviePosterPath, String mOverview) {
        this.mMovieId = mMovieId;
        this.mMovieTitle = mMovieTitle;
        this.mMoviePosterPath = mMoviePosterPath;
        this.mOverview = mOverview;
    }

    public long getMovieId() {
        return mMovieId;
    }

    public void setMovieId(long movieId) {
        mMovieId = movieId;
    }

    public String getMovieTitle() {
        return mMovieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.mMovieTitle = movieTitle;
    }

    public String getMoviePosterPath() {
        return mMoviePosterPath;
    }

    public void setMoviePosterPath(String moviePosterPath) {
        this.mMoviePosterPath = moviePosterPath;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String mOverview) {
        this.mOverview = mOverview;
    }
}
