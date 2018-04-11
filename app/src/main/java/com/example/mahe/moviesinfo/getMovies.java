package com.example.mahe.moviesinfo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.youtube.player.YouTubePlayer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Mahe on 6/5/2017.
 */

public class getMovies {
  static   Context mContext;
    static String MOVIE_REQUEST_COMMON_URL="https://api.themoviedb.org/3/movie/now_playing";
   static public final String API_KEY="api_key";
    static public final String LANGUAGE="language";
    static public final String MY_LANGUAGE="en-US";
    static public final String MY_API_KEY="320a42159bd2a6bf636fb3bd7b4ba507";
    public static final String VIDEO_REQUEST_URL="https://api.themoviedb.org/3/movie/";
    static VideoView videoView;

    public static URL GetLatestMovies()
    {
        Uri query_for_latest_movies=Uri.parse(MOVIE_REQUEST_COMMON_URL).buildUpon().appendQueryParameter(API_KEY,MY_API_KEY).appendQueryParameter(LANGUAGE,MY_LANGUAGE).build();
        try {
            URL lattest_movie_url=new URL(query_for_latest_movies.toString());
            return lattest_movie_url;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;

        }

    }

    public static void GetMoviePoster(final String posterId,final ImageView view) {
        final Bitmap[] mBitmap = new Bitmap[1];

            AsyncTask<Void, Void, Bitmap> task = new AsyncTask<Void, Void, Bitmap>() {
                @Override
                protected Bitmap doInBackground(Void... params) {
                    Bitmap bitmap= null;
                    try {
                        bitmap = BitmapFactory.decodeStream((new URL("https://image.tmdb.org/t/p/w500/" + posterId)).openConnection().getInputStream());
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    return bitmap;
                }

                @Override
                protected void onPostExecute(Bitmap bitmap) {
                    view.setImageBitmap(bitmap);
                }
            };
            task.execute();



        }
        public static String getVideoKey(String movie_id) throws MalformedURLException {
            final String[] trailer_key = new String[1];

            final String uri="https://api.themoviedb.org/3/movie/"+movie_id+"/videos?api_key=320a42159bd2a6bf636fb3bd7b4ba507&language=en-US";
               Uri query_for_trailer=Uri.parse(uri).buildUpon().appendQueryParameter(API_KEY,MY_API_KEY).appendQueryParameter(LANGUAGE,MY_LANGUAGE).build();
            URL  url=new URL(uri);
            try {
                String trailerjson=openHTTPurlconnection(url);
                String key=get_trailer_key(trailerjson);
                return key;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }




        }

    public static String get_trailer_key(String trailerjson) {
        try {
            JSONObject json=new JSONObject(trailerjson);
            JSONArray jsonarray=json.getJSONArray("results");
            JSONObject each_movie=jsonarray.getJSONObject(0);

            return each_movie.getString("key");

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String openHTTPurlconnection(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
    public static void playVideo(final String key, VideoView view, Context context)
    {

    }

}


