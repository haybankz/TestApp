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
import com.example.testapp.ui.MovieDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteVH> {

    private List<Favourite> mFavourites;
    private Context mContext;

    public FavouriteAdapter(Context context, List<Favourite> favourites){
        mFavourites = favourites;
        mContext = context;
    }

    @NonNull
    @Override
    public FavouriteVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);

        return new FavouriteVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteVH holder, int position) {
        Favourite favourite = mFavourites.get(position);
        holder.bindFavourite(favourite);
    }

    @Override
    public int getItemCount() {
        return mFavourites.size();
    }

    public void setFavourites(List<Favourite> favourites){
        mFavourites = favourites;
        notifyDataSetChanged();
    }

    class FavouriteVH extends RecyclerView.ViewHolder{

        private TextView titleTextView;
        private TextView overviewTextView;
        private ImageView posterImageView;
        private ImageView favouriteImageView;
        private TextView seeMoreTextView;

        FavouriteVH(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.textview_title);
            overviewTextView = itemView.findViewById(R.id.textview_overview);
            posterImageView = itemView.findViewById(R.id.imageview_poster);
            favouriteImageView = itemView.findViewById(R.id.imageView_fav);
            seeMoreTextView = itemView.findViewById(R.id.textview_see_more);
            favouriteImageView.setImageResource(R.drawable.ic_favorite_full);

        }

        void bindFavourite(final Favourite favourite){
            titleTextView.setText(favourite.getMovieTitle());
            overviewTextView.setText(favourite.getOverview());
            Picasso.get().load(Utils.getSmallPosterPath(favourite.getMoviePosterPath()))
                    .error(R.drawable.ic_launcher_foreground)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(posterImageView);

            favouriteImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FavouriteService favouriteService = new FavouriteService(mContext);

                        int result = favouriteService.removeFavourite(favourite.getMovieId());
                        if(result > 0) Toast.makeText(mContext, R.string.movie_removed_from_favourites, Toast.LENGTH_SHORT).show();



                }
            });

            seeMoreTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, MovieDetailActivity.class);
                    Bundle b = new Bundle();
                    b.putLong(Constant.MOVIE_ID, favourite.getMovieId());
                    intent.putExtras(b);
                    mContext.startActivity(intent);
                }
            });

        }
    }


}
