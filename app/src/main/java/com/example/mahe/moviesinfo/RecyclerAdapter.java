package com.example.mahe.moviesinfo;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mahe.moviesinfo.Provider.MoviesContract;

/**
 * Created by Mahe on 6/5/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MovieViewHolder> {
  Context mContext;
    Cursor mCursor;
    MovieRecyclerClick movierecyclerclick;
    public interface MovieRecyclerClick
    {
        public void onMovieClick(int position);

    }
    public RecyclerAdapter(Context context,MovieRecyclerClick click)
    {
        mContext=context;
        movierecyclerclick=click;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.individual_item_layout,parent,false);
        view.setFocusable(true);
        return new MovieViewHolder(view);


    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
          if(mCursor==null)
          {
              return;
          }
          mCursor.moveToPosition(position);
        int Coloumn_movie_title_index= mCursor.getColumnIndex(MoviesContract.MoviesEntry.COLOUMN_MOVIE_NAME);
        int Coloumn_movie_release_date_index=mCursor.getColumnIndex(MoviesContract.MoviesEntry.COLOUMN_RELEASE_DATE);
        int Coloumn_movie_poster=mCursor.getColumnIndex(MoviesContract.MoviesEntry.COLOUMN_MOVIE_POSTER_ID);
        String poster_id=mCursor.getString(Coloumn_movie_poster);
        getMovies.GetMoviePoster(poster_id,holder.Movie_Poster);

        holder.itemView.setTag(position);
        holder.movie_title_view.setText(mCursor.getString(Coloumn_movie_title_index));
        holder.Release_Date_view.setText(mCursor.getString(Coloumn_movie_release_date_index));

    }

    @Override
    public int getItemCount() {
        if(mCursor!=null) {
            return mCursor.getCount();
        }
        else
            return 0;
    }
    public void swapCursor(Cursor cursor)
    {

        mCursor=cursor;
        notifyDataSetChanged();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
       public TextView movie_title_view;
        public TextView Release_Date_view;
        public ImageView Movie_Poster;
        public MovieViewHolder(View itemView) {
            super(itemView);
            movie_title_view=(TextView)itemView.findViewById(R.id.Movie_title);
            Release_Date_view=(TextView)itemView.findViewById(R.id.movie_release_date);
            Movie_Poster=(ImageView)itemView.findViewById(R.id.this_poster);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view)
        {
            int position=getAdapterPosition();
            movierecyclerclick.onMovieClick(position);

        }

    }

}
