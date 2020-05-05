package com.example.testapp.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.testapp.R;
import com.example.testapp.Utils;
import com.example.testapp.adapter.MovieAdapter;
import com.example.testapp.data.remote.movie.MovieRepository;
import com.example.testapp.model.Movie;
import com.example.testapp.model.NowPlayingResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NowPlayingFragment extends Fragment {



    private RelativeLayout errorLayout;
    private RelativeLayout loadingLayout;
    private TextView errorTextView;

    private RecyclerView nowPlayingRecyclerView;
    private MovieAdapter adapter;

    static NowPlayingFragment newInstance() {
        return new NowPlayingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.now_playing_fragment, container, false);

        //instantiate views
        errorLayout = v.findViewById(R.id.layout_error);
        loadingLayout = v.findViewById(R.id.layout_loading);
        errorTextView = v.findViewById(R.id.textview_error);
        Button retryButton = v.findViewById(R.id.button_retry);

        //set onclick listener for retry button in error layout
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

        //instantiate recyclerview
        nowPlayingRecyclerView = v.findViewById(R.id.recyclerview_now_playing);

//        instatiate adapter with empty arraylist
        adapter = new MovieAdapter(requireContext(), new ArrayList<Movie>());

        //set adapter to recyclerview
        nowPlayingRecyclerView.setAdapter(adapter);

        //get remote data
        getData();
        return v;
    }


    private void getData(){
        //show loading layout
        showLoading();

        //make remote data call
       MovieRepository.getInstance().getNowPlaying().enqueue(new Callback<NowPlayingResponse>() {
           @Override
           public void onResponse(Call<NowPlayingResponse> call, Response<NowPlayingResponse> response) {
               if(response.isSuccessful()){
                   if (response.body() != null) {
                       adapter.setMovies(response.body().getResults());
                   }
                   showData();
               }else{

                   if (response.errorBody() != null) {
                       showError(Utils.parseError(response.errorBody()));
                   }
               }
           }

           @Override
           public void onFailure(Call<NowPlayingResponse> call, Throwable t) {
              showError(t.getMessage());
           }
       });
    }


    private void showLoading(){
        loadingLayout.setVisibility(View.VISIBLE);
        errorLayout.setVisibility(View.GONE);
        nowPlayingRecyclerView.setVisibility(View.GONE);
    }

    private void showError(String errorMessage){
        loadingLayout.setVisibility(View.GONE);
        nowPlayingRecyclerView.setVisibility(View.GONE);
        errorTextView.setText(errorMessage);
        errorLayout.setVisibility(View.VISIBLE);
    }

    private void showData(){
        loadingLayout.setVisibility(View.GONE);
        errorLayout.setVisibility(View.GONE);
        nowPlayingRecyclerView.setVisibility(View.VISIBLE);
    }

}
