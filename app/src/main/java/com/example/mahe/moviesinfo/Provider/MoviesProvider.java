package com.example.mahe.moviesinfo.Provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Mahe on 6/5/2017.
 */

public class MoviesProvider extends ContentProvider {
    public static final int DATABASE_IN_GENERAL=100;
    public Movie_Database mdatabase;
    public UriMatcher sUriMatcher = buildUriMatcher();
    @Override
    public boolean onCreate() {
        mdatabase=new Movie_Database(getContext());
        return true;
    }
    public static UriMatcher buildUriMatcher()
    {
        UriMatcher uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        String authority=MoviesContract.CONTENT_URI;
        uriMatcher.addURI(authority, MoviesContract.paths,DATABASE_IN_GENERAL);
        return uriMatcher;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor;
        SQLiteDatabase mOpenHelper=mdatabase.getReadableDatabase();
        switch (sUriMatcher.match(uri))
        {
            case DATABASE_IN_GENERAL:
                cursor=mOpenHelper.query(MoviesContract.MoviesEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
                default:throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Uri returnuri=null;
        SQLiteDatabase mOpenHelper=mdatabase.getReadableDatabase();
        switch (sUriMatcher.match(uri))
        {
            case DATABASE_IN_GENERAL:
            long id=   mOpenHelper.insert(MoviesContract.MoviesEntry.TABLE_NAME,null,values);
                if(id>0)
                {
                    returnuri= ContentUris.withAppendedId(MoviesContract.MoviesEntry.FINAL_URI, id);
                }
                break;
            default:throw new UnsupportedOperationException("Unknown uri: " + uri);


        }
        getContext().getContentResolver().notifyChange(uri, null);
          return returnuri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase mOpenHelper=mdatabase.getWritableDatabase();
        int number;
        switch(sUriMatcher.match(uri))
        {
            case DATABASE_IN_GENERAL:number=mOpenHelper.delete(MoviesContract.MoviesEntry.TABLE_NAME,selection,selectionArgs);
                break;
            default:throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (number != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
       return number;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
