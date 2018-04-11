package com.example.mahe.moviesinfo;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.net.MalformedURLException;


/**
 * A simple {@link Fragment} subclass.
 */
public class TrailerAndOverview extends Fragment {


    public TrailerAndOverview() {
        // Required empty public constructor
    }

   String movie_id;
    String overView;

    TextView OverViewTextView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_trailer_and_overview, container, false);
        movie_id=getArguments().getString(MovieDetail.MOVIE_ID);
        overView=getArguments().getString(MovieDetail.MOVIE_OVERVIEW);
        OverViewTextView=(TextView)view.findViewById(R.id.Overview);
        OverViewTextView.setText(overView);
        YouTubePlayerSupportFragment myYouTubePlayerFragment=YouTubePlayerSupportFragment.newInstance();
        FragmentTransaction transaction=getChildFragmentManager().beginTransaction();
        transaction.add(R.id.youtube,myYouTubePlayerFragment).commit();
        myYouTubePlayerFragment.initialize(Config.Developer_key, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer youTubePlayer, boolean b) {
                    if(!b)
                    {
                        AsyncTask<Void,Void,String> task=new AsyncTask<Void, Void, String>() {
                            @Override
                            protected String doInBackground(Void... params) {
                                try {
                                    String key=  getMovies.getVideoKey(movie_id);
                                    return key;
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                    return null;
                                }
                            }
                            @Override
                            protected void onPostExecute(String key)
                            {
                                youTubePlayer.loadVideo(key);
                            }
                        };
                        task.execute();


                        //youTubePlayer.loadVideo();
                        youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                    }
                    }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {

            }
        });




        return view;

    }

}
