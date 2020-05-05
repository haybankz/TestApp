package com.example.testapp.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.testapp.model.Favourite;
import com.example.testapp.model.Movie;


@Database(entities = {Favourite.class}, version = 2, exportSchema = false)
public abstract class AppDatabase  extends RoomDatabase {

    private static AppDatabase appDatabase = null;

    /* @param context : Context
     * returns  appDatabase  : AppDatabase
     * this method creates and returns a singleton instance of appDatabase
     * */
    public static AppDatabase getInstance(Context context) {
        if (appDatabase == null) {
            // define the appDatabase with the database name 'testApptDb'.
            appDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "testAppDb")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return appDatabase;
    }


    //declare an instance of Favourite Dao
    public abstract FavouriteDao favouriteDao();

}
