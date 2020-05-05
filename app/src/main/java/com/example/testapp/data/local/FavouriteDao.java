package com.example.testapp.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.testapp.model.Favourite;

import java.util.List;

@Dao
public interface FavouriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertFavourite(Favourite favourite);

    @Query("select * from favourite")
    LiveData<List<Favourite>> getAllFavourites();

    @Query("delete from favourite where movie_id = :movieId")
    int removeFavourite(long movieId);


}
