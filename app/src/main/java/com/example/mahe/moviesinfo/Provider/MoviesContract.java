package com.example.mahe.moviesinfo.Provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Mahe on 6/5/2017.
 */

public class MoviesContract {
public static final String CONTENT_URI="com.example.mahe.moviesinfo";
    public  static final Uri BASE_CONTENT_URI=Uri.parse("content://"+CONTENT_URI);
    public static final String paths="Movies";
    public static class MoviesEntry implements BaseColumns{
    public static Uri FINAL_URI=BASE_CONTENT_URI.buildUpon().appendPath(paths).build();

    public static final String TABLE_NAME="Movies";
        public static final String COLOUMN_MOVIE_ID="movie_id";
        public static final String COLOUMN_MOVIE_NAME="movie_name";
        public static final String COLOUMN_OVERVIEW="overview";
        public static final String COLOUMN_RELEASE_DATE="release_date";
        public static final String COLOUMN_TMDB_MOVIE_ID="TMBD_id";
        public static final String COLOUMN_MOVIE_POSTER_ID="poster_id";
    }
}
