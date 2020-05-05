package com.example.testapp.data.local;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.testapp.model.Favourite;


import java.util.List;

public class FavouriteService {

    private FavouriteDao favouriteDao;

    public FavouriteService(Context context){
        favouriteDao = AppDatabase.getInstance(context).favouriteDao();
    }

    public LiveData<List<Favourite>> getAllFavourites(){
        return favouriteDao.getAllFavourites();
    }

    public long insertFavourite(Favourite favourite){
        return favouriteDao.insertFavourite(favourite);
    }

    public int removeFavourite(long movieId){
        return favouriteDao.removeFavourite(movieId);
    }





}
