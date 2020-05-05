package com.example.testapp.data.remote.movie;

import com.example.testapp.model.MovieDetailResponse;
import com.example.testapp.model.NowPlayingResponse;
import com.example.testapp.model.UpcomingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    @GET("movie/now_playing")
    Call<NowPlayingResponse> getNowPlaying(@Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Call<UpcomingResponse> getUpcoming(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}")
    Call<MovieDetailResponse> getMovieDetail(@Path("movie_id") long movieId,
                                             @Query("api_key") String apiKey
                                             );

}
