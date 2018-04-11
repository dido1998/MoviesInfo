package com.example.mahe.moviesinfo;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mahe.moviesinfo.Insert.AllBackend;
import com.example.mahe.moviesinfo.Insert.InsertDataService;
import com.example.mahe.moviesinfo.Provider.MoviesContract;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>,RecyclerAdapter.MovieRecyclerClick{
    LinearLayoutManager layoutManager;
    private int mPosition = RecyclerView.NO_POSITION;
    RecyclerView mRecyclerView;
    RecyclerAdapter adapter;
    Cursor mCursor;
    public static final int LOADER_ID=69;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView=(RecyclerView)findViewById(R.id.Movie_View);
        layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);


        /* setLayoutManager associates the LayoutManager we created above with our RecyclerView */
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
     adapter=new RecyclerAdapter(this,this);
        mRecyclerView.setAdapter(adapter);

        AllBackend.initialize(this);
        getSupportLoaderManager().initLoader(LOADER_ID,null,this);
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        if(id==LOADER_ID) {
            return new CursorLoader(this, MoviesContract.MoviesEntry.FINAL_URI, null, null, null, null);
        }
        else
        {
            throw new RuntimeException("Loader Not Implemented: " + id);
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursor=data;
        if(data!=null)
        {
             adapter.swapCursor(data);
            if (mPosition == RecyclerView.NO_POSITION) mPosition = 0;
            layoutManager.scrollToPositionWithOffset(mPosition,0);
        }

    }




    @Override
    public void onLoaderReset(Loader loader) {
adapter.swapCursor(null);
    }

    @Override
    public void onMovieClick(int position) {
        Intent intent=new Intent(this,MovieDetail.class);
        mCursor.moveToPosition(position);
        int index_id=mCursor.getColumnIndex(MoviesContract.MoviesEntry.COLOUMN_TMDB_MOVIE_ID);
        int index_overview=mCursor.getColumnIndex(MoviesContract.MoviesEntry.COLOUMN_OVERVIEW);
        int movie_id=mCursor.getInt(index_id);
        String overview=mCursor.getString(index_overview);
        intent.putExtra(MovieDetail.MOVIE_ID,movie_id);
        intent.putExtra(MovieDetail.MOVIE_OVERVIEW,overview);
        startActivity(intent);



    }
}
