package com.example.mahe.moviesinfo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Mahe on 6/7/2017.
 */

public class GetCredits {
    String base_url="https://api.themoviedb.org/3/movie/";
    ArrayList<credit_list> credits=new ArrayList<>();
    public ArrayList getCreditDta(String movie_id)
    {
        String url=base_url+movie_id+"/credits?api_key=320a42159bd2a6bf636fb3bd7b4ba507";
        try {
            URL credit_url=new URL(url);
            String response=getMovies.openHTTPurlconnection(credit_url);
            credits=getlistofcredits(response);
            return credits;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ArrayList<credit_list> getlistofcredits(String response) {
        try {
            ArrayList<credit_list> temp_list=new ArrayList<>();
            JSONObject jsonobject = new JSONObject(response);
            JSONArray jsonarray = jsonobject.getJSONArray("cast");
            for (int i = 0; i < jsonarray.length(); i++)
            {
                JSONObject each_person=jsonarray.getJSONObject(i);
                final Bitmap[] picture = new Bitmap[1];
                String name=each_person.getString("name");
                String character=each_person.getString("character");
                final String profile_path=each_person.getString("profile_path");


                credit_list list=new credit_list(name,character,profile_path);
                temp_list.add(list);

            }
            return temp_list;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void getProfilePicture(final String profile_path, final ImageView view, Context context) throws MalformedURLException {
        final Bitmap[] biitmap = new Bitmap[1];
     AsyncTask<Void,Void,Bitmap> task=new AsyncTask<Void, Void, Bitmap>() {
         @Override
         protected Bitmap doInBackground(Void... params) {
             try {
                 Bitmap bitmap = BitmapFactory.decodeStream((new URL("https://image.tmdb.org/t/p/w500/" + profile_path)).openConnection().getInputStream());
                 return bitmap;
             } catch (IOException e) {
                 e.printStackTrace();
                 return null;
             }
         }
         @Override
         protected void onPostExecute(Bitmap bitmap)
         {
        view.setImageBitmap(bitmap);
         }


     };

     task.execute();


    }



}
