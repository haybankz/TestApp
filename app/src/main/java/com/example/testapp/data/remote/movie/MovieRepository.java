package com.example.testapp.data.remote.movie;


import com.example.testapp.Constant;
import com.example.testapp.data.remote.APIClient;
import com.example.testapp.model.MovieDetailResponse;
import com.example.testapp.model.NowPlayingResponse;
import com.example.testapp.model.UpcomingResponse;

import retrofit2.Call;

public class MovieRepository {

    private static MovieRepository movieRepository;

    private MovieService movieService;

    public static MovieRepository getInstance(){
        if (movieRepository == null){
            movieRepository = new MovieRepository();
        }
        return movieRepository;
    }

    private MovieRepository(){
        movieService = APIClient.createService(MovieService.class);
    }

    public Call<NowPlayingResponse> getNowPlaying(){
        return movieService.getNowPlaying(Constant.API_KEY);
    }

    public Call<UpcomingResponse> getUpComing(){
        return movieService.getUpcoming(Constant.API_KEY);
    }

    public Call<MovieDetailResponse> getMovieDetail(long movieId){
        return movieService.getMovieDetail(movieId,Constant.API_KEY);
    }

}
