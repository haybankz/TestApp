package com.example.testapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapp.Constant;
import com.example.testapp.R;
import com.example.testapp.Utils;
import com.example.testapp.data.local.FavouriteService;
import com.example.testapp.data.remote.movie.MovieRepository;
import com.example.testapp.model.Favourite;
import com.example.testapp.model.Genre;
import com.example.testapp.model.Movie;
import com.example.testapp.model.MovieDetailResponse;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity {


    private RelativeLayout loadingLayout;
    private RelativeLayout errorLayout;
    private TextView errorTextView;


    private LinearLayout detailLayout;
    private ImageView posterImageView;
    private TextView titleTextView;
    private TextView overviewTextView;
    private ChipGroup genreChipGroup;
    private TextView averageVotesTextView;
    private TextView totalVotesTextView;
    private ImageView favouriteImageView;
    private ImageView shareImageView;

    long movieId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        //check if activity has bundle
        Bundle b = getIntent().getExtras();

        //if activity is null, close the activity
        if(b == null) finish();

        //get movie id from bundle
        movieId  =  b.getLong(Constant.MOVIE_ID);

        //if bundle does not include movie id, close fragment
        if( movieId == 0) finish();

        //instantiate views
        loadingLayout = findViewById(R.id.layout_loading);
        errorLayout = findViewById(R.id.layout_error);
        errorTextView = findViewById(R.id.textview_error);
        Button retryButton  = findViewById(R.id.button_retry);


        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });


        detailLayout = findViewById(R.id.layout_detail);
        posterImageView = findViewById(R.id.imageview_poster);
        titleTextView = findViewById(R.id.textview_title);
        overviewTextView = findViewById(R.id.textview_overview);
        genreChipGroup = findViewById(R.id.chipgroup_genre);
        averageVotesTextView = findViewById(R.id.textview_average_votes);
        totalVotesTextView = findViewById(R.id.textview_total_votes);
        favouriteImageView = findViewById(R.id.imageview_favourite);
        shareImageView = findViewById(R.id.imageview_share);


        //get movie detail data
        getData();


    }

    //set movie detail data to views
    private void setData(final MovieDetailResponse movie){
        String url = "";

        //if movie has backdrop picture show it, else show poster picture
       url =  movie.getBackdropPath().isEmpty() ? movie.getPosterPath() : movie.getBackdropPath();
       Picasso.get().load(Utils.getBigPosterPath(url))
                .error(R.drawable.ic_launcher_foreground)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(posterImageView);

//       set title and overview text
        titleTextView.setText(movie.getTitle());
        overviewTextView.setText(movie.getOverview());


        //iterate through the list of genre and represent each with a chip
        for(Genre genre : movie.getGenres()){
            Chip chip = getChip(genre.getName());
            genreChipGroup.addView(chip);
        }

//        set average and total votes text
        averageVotesTextView.setText(String.format("%s %s", movie.getVoteAverage(), getString(R.string.average_votes)));
        totalVotesTextView.setText(String.format("%s %s", movie.getVoteCount(), getString(R.string.total_votes)));

//        favourite the movie onclick of the heart icon
        favouriteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Favourite favourite = Utils.getFavouriteFromMovieDetail(movie);
                long result = new FavouriteService(MovieDetailActivity.this).insertFavourite(favourite);
                if(result > 0)
                    Toast.makeText(MovieDetailActivity.this, getString(R.string.movie_removed_from_favourites), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MovieDetailActivity.this, R.string.movie_already_added_to_favourite, Toast.LENGTH_SHORT).show();
            }
        });


        // share movie on media platforms
        shareImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareText(movie);
            }
        });

    }

    //share text  method
    public void shareText( MovieDetailResponse movie) {
        Intent txtIntent = new Intent(android.content.Intent.ACTION_SEND);
        txtIntent .setType("text/plain");
        String body = Utils.movieToShareableText(this, movie);
        txtIntent .putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.app_name));
        txtIntent .putExtra(android.content.Intent.EXTRA_TEXT, body);

        startActivity(Intent.createChooser(txtIntent ,"Share"));
    }

    // creates an d returns individual chips
    private Chip getChip(final String text){

        final Chip chip = new Chip(this);
        chip.setChipDrawable(ChipDrawable.createFromResource(this, R.xml.chip));
        int paddingDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
        chip.setPadding(paddingDp, 0, paddingDp, 0);
        chip.setText(text);
        chip.setTag(text);

        return chip;
    }

    //get remote data
    private void getData(){
        showLoading();
        MovieRepository.getInstance().getMovieDetail(movieId).enqueue(new Callback<MovieDetailResponse>() {
            @Override
            public void onResponse(Call<MovieDetailResponse> call, Response<MovieDetailResponse> response) {
                if(response.isSuccessful()){
                    setData(response.body());
                    showData();
                }else{
                    showError(Utils.parseError(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<MovieDetailResponse> call, Throwable t) {
                showError(t.getMessage());
            }
        });
    }

    private void showLoading(){

        loadingLayout.setVisibility(View.VISIBLE);
        errorLayout.setVisibility(View.GONE);
        detailLayout.setVisibility(View.GONE);
    }

    private void showError(String errorMessage){
        loadingLayout.setVisibility(View.GONE);
        detailLayout.setVisibility(View.GONE);
        errorTextView.setText(errorMessage);
        errorLayout.setVisibility(View.VISIBLE);
    }

    private void showData(){
        loadingLayout.setVisibility(View.GONE);
        errorLayout.setVisibility(View.GONE);
        detailLayout.setVisibility(View.VISIBLE);
    }
}
