package com.example.mahe.moviesinfo;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.mahe.moviesinfo.Provider.MoviesContract;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.net.MalformedURLException;

public class MovieDetail extends AppCompatActivity{
public static final String MOVIE_ID="ID";
    private static final int RECOVERY_DIALOG_REQUEST = 1;


    int movie_id;
    String overView;

    public static final String MOVIE_OVERVIEW="overview";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
         movie_id=getIntent().getIntExtra(MOVIE_ID,-1);
        overView=getIntent().getStringExtra(MOVIE_OVERVIEW);
        TrailerAndOverview frag=new TrailerAndOverview();
        FragmentManager manager=getSupportFragmentManager();
        Bundle bundle=new Bundle();
        bundle.putString(MOVIE_ID, String.valueOf(movie_id));
        bundle.putString(MOVIE_OVERVIEW,overView);
        frag.setArguments(bundle);
        manager.beginTransaction().add(R.id.youtube_fargment,frag).commit();
        Bundle bundle1=new Bundle();
        bundle1.putString(MOVIE_ID, String.valueOf(movie_id));
        PeopleView fragment=new PeopleView();
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragment.setArguments(bundle1);
        fragmentManager.beginTransaction().add(R.id.credits_view,fragment).commit();

    }









}
