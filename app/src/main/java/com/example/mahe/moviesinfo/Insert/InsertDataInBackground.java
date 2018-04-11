package com.example.mahe.moviesinfo.Insert;

import android.content.Context;
import android.os.AsyncTask;

import com.example.mahe.moviesinfo.JSONparser;
import com.example.mahe.moviesinfo.MainActivity;
import com.example.mahe.moviesinfo.Provider.MoviesContract;
import com.example.mahe.moviesinfo.getMovies;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Mahe on 6/5/2017.
 */

public class InsertDataInBackground {
    public static void insertData(final Context context)
    {
        context.getContentResolver().delete(MoviesContract.MoviesEntry.FINAL_URI,null,null);

        AsyncTask<Void,Void,String> task=new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                URL url = getMovies.GetLatestMovies();
                try {
                    String json=getMovies.openHTTPurlconnection(url);
                    return json;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            @Override
            protected void onPostExecute(String json)
            {
                JSONparser.getandinsertdata(json,context);
            }
        };
        task.execute();
    }
}
