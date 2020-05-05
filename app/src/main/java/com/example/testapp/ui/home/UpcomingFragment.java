package com.example.testapp.ui.home;

import androidx.lifecycle.ViewModelProviders;

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
import com.example.testapp.model.UpcomingResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpcomingFragment extends Fragment {

    private RelativeLayout loadingLayout;
    private RelativeLayout errorLayout;
    private TextView errorTextView;

    private RecyclerView upcomingRecyclerView;
    private MovieAdapter adapter;

    static UpcomingFragment newInstance() {
        return new UpcomingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.upcoming_fragment, container, false);

        //instantiate views
        loadingLayout = v.findViewById(R.id.layout_loading);
        errorLayout = v.findViewById(R.id.layout_error);
        errorTextView = v.findViewById(R.id.textview_error);
        Button retryButton = v.findViewById(R.id.button_retry);

        upcomingRecyclerView = v.findViewById(R.id.recyclerview_upcoming);
        adapter = new MovieAdapter(requireContext(), new ArrayList<Movie>());

        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

        upcomingRecyclerView.setAdapter(adapter);

        //get remote data
        getData();

        return v;
    }

    private void getData(){
        showLoading();
        MovieRepository.getInstance().getUpComing().enqueue(new Callback<UpcomingResponse>() {
            @Override
            public void onResponse(Call<UpcomingResponse> call, Response<UpcomingResponse> response) {
                if(response.isSuccessful()){
                    if (response.body() != null) {
                        adapter.setMovies(response.body().getResults());
                    }else{
                        showError("No data to show");
                    }
                    showData();
                }else{
                    showError(Utils.parseError(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<UpcomingResponse> call, Throwable t) {
                showError(t.getMessage());
            }
        });

    }


    private void showLoading(){
        loadingLayout.setVisibility(View.VISIBLE);
        errorLayout.setVisibility(View.GONE);
        upcomingRecyclerView.setVisibility(View.GONE);
    }

    private void showError(String errorMessage){
        loadingLayout.setVisibility(View.GONE);
        upcomingRecyclerView.setVisibility(View.GONE);
        errorTextView.setText(errorMessage);
        errorLayout.setVisibility(View.VISIBLE);
    }

    private void showData(){
        loadingLayout.setVisibility(View.GONE);
        errorLayout.setVisibility(View.GONE);
        upcomingRecyclerView.setVisibility(View.VISIBLE);
    }



}
