package com.example.mahe.moviesinfo.Provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mahe on 6/5/2017.
 */

public class Movie_Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="movie.db";
    public static final int DATABASE_VERSION=2;
    public Movie_Database(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_MOVIE_TABLE="CREATE TABLE "+ MoviesContract.MoviesEntry.TABLE_NAME+" ("+ MoviesContract.MoviesEntry.COLOUMN_MOVIE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+ MoviesContract.MoviesEntry.COLOUMN_MOVIE_NAME+" TEXT NOT NULL,"+ MoviesContract.MoviesEntry.COLOUMN_OVERVIEW+" TEXT NOT NULL,"+MoviesContract.MoviesEntry.COLOUMN_RELEASE_DATE+" TEXT NOT NULL,"+MoviesContract.MoviesEntry.COLOUMN_TMDB_MOVIE_ID+" INTEGER NOT NULL,"+ MoviesContract.MoviesEntry.COLOUMN_MOVIE_POSTER_ID+" TEXT NOT NULL"+");";
        db.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      String UPGRADE="DROP TABLE IF EXISTS "+ MoviesContract.MoviesEntry.TABLE_NAME;
        db.execSQL(UPGRADE);
        onCreate(db);
    }
}
