package com.example.mahe.moviesinfo;

import android.content.ContentValues;
import android.content.Context;

import com.example.mahe.moviesinfo.Provider.MoviesContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Mahe on 6/5/2017.
 */

public class JSONparser {
    public static void getandinsertdata(String json, Context context){
        try {
            JSONObject moviejson=new JSONObject(json);
            JSONArray results=moviejson.getJSONArray("results");
            for(int i=0;i<results.length();i++)
            {
                JSONObject eachmovie=  results.getJSONObject(i);
                int tmdb_movie_id=eachmovie.getInt("id");
                String title=eachmovie.getString("title");
                String overview=eachmovie.getString("overview");
                String release_Date=eachmovie.getString("release_date");
                String Movie_Poster=eachmovie.getString("poster_path");
                ContentValues values=new ContentValues();
                values.put(MoviesContract.MoviesEntry.COLOUMN_MOVIE_NAME,title);
                values.put(MoviesContract.MoviesEntry.COLOUMN_OVERVIEW,overview);
                values.put(MoviesContract.MoviesEntry.COLOUMN_RELEASE_DATE,release_Date);
                values.put(MoviesContract.MoviesEntry.COLOUMN_TMDB_MOVIE_ID,tmdb_movie_id);
                values.put(MoviesContract.MoviesEntry.COLOUMN_MOVIE_POSTER_ID,Movie_Poster);
                insert(values,context);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private static void insert(ContentValues values,Context context) {
        context.getContentResolver().insert(MoviesContract.MoviesEntry.FINAL_URI,values);


    }

}
