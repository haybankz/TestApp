package com.example.testapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.Constant;
import com.example.testapp.R;
import com.example.testapp.Utils;
import com.example.testapp.data.local.FavouriteService;
import com.example.testapp.model.Favourite;
import com.example.testapp.model.Movie;
import com.example.testapp.ui.MovieDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieVH> {

    private List<Movie> mMovies;
    private Context mContext;

    public MovieAdapter(Context context, List<Movie> movies){
        mMovies = movies;
        mContext = context;
    }

    @NonNull
    @Override
    public MovieVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);

        return new  MovieVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieVH holder, int position) {
        Movie movie = mMovies.get(position);
        holder.bindMovie(movie);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public void setMovies(List<Movie> movies){
        mMovies = movies;
        notifyDataSetChanged();
    }

    class MovieVH extends RecyclerView.ViewHolder{

        private TextView titleTextView;
        private TextView overviewTextView;
        private ImageView posterImageView;
        private ImageView favouriteImageView;
        private TextView seeMoreTextView;

        MovieVH(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.textview_title);
            overviewTextView = itemView.findViewById(R.id.textview_overview);
            posterImageView = itemView.findViewById(R.id.imageview_poster);
            favouriteImageView = itemView.findViewById(R.id.imageView_fav);
            seeMoreTextView = itemView.findViewById(R.id.textview_see_more);
            favouriteImageView.setImageResource(R.drawable.ic_favorite_border);

        }

        void bindMovie(final Movie movie){
            titleTextView.setText(movie.getTitle());
            overviewTextView.setText(movie.getOverview());
            Picasso.get().load(Utils.getSmallPosterPath(movie.getPosterPath()))
                    .error(R.drawable.ic_launcher_foreground)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(posterImageView);

            favouriteImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FavouriteService favouriteService = new FavouriteService(mContext);

                    Favourite favourite = Utils.getFavouriteFromMovie(movie);
                        long result = favouriteService.insertFavourite(favourite);
                        if(result > 0) Toast.makeText(mContext, R.string.movie_added_to_favourites, Toast.LENGTH_SHORT).show();
                        else Toast.makeText(mContext, R.string.movie_already_added_to_favourite, Toast.LENGTH_SHORT).show();


                }
            });

            seeMoreTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, MovieDetailActivity.class);
                    Bundle b = new Bundle();
                    b.putLong(Constant.MOVIE_ID, movie.getId());
                    intent.putExtras(b);
                    mContext.startActivity(intent);
                }
            });

        }
    }


}
