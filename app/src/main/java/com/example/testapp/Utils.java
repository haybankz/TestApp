package com.example.testapp;

import android.content.Context;
import android.content.Intent;

import com.example.testapp.model.ErrorResponse;
import com.example.testapp.model.Favourite;
import com.example.testapp.model.Movie;
import com.example.testapp.model.MovieDetailResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.ResponseBody;

public class Utils {

    //get message from response error body
    public static String parseError(ResponseBody response){

        if(response == null) return "Unknown error";
        Gson gson = new Gson();
        Type type = new TypeToken<ErrorResponse>() {}.getType();
        ErrorResponse errorResponse = gson.fromJson(response.charStream(),type);
        return errorResponse.getStatusMessage();
    }

    //complete the poster path, the endpoints only return the poster name
    public static String getSmallPosterPath(String path){
        StringBuilder sb = new StringBuilder();
        sb.append("https://image.tmdb.org/t/p/w185/");
        sb.append(path);

        return sb.toString();
    }

    //also complete the poster path, but this returns a bigger image, preferrably for backdrop posters
    public static String getBigPosterPath(String path){
        StringBuilder sb = new StringBuilder();
        sb.append("https://image.tmdb.org/t/p/w342/");
        sb.append(path);

        return sb.toString();
    }

    public static String movieToShareableText(Context context, MovieDetailResponse movie){

        StringBuilder builder = new StringBuilder();
        return builder.append(movie.getTitle())
                .append("\n")
                .append(movie.getOverview())
                .append("\n\n\n")
                .append(context.getString(R.string.shared_from)).append(" \n\"").append(context.getString(R.string.app_name)).append("\"")
                .toString();

    }



    public static Favourite getFavouriteFromMovie(Movie movie){
        return new Favourite(movie.getId(), movie.getTitle(), movie.getPosterPath(), movie.getOverview());
    }

    public static Favourite getFavouriteFromMovieDetail(MovieDetailResponse movie){
        return new Favourite(movie.getId(), movie.getTitle(), movie.getPosterPath(), movie.getOverview());
    }


}
