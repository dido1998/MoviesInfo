package com.example.mahe.moviesinfo.Insert;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;

import com.example.mahe.moviesinfo.Provider.MoviesContract;
import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

import java.util.concurrent.TimeUnit;

/**
 * Created by Mahe on 6/6/2017.
 */

public class AllBackend   {
    private static final int SYNC_INTERVAL_HOURS = 48;
    private static final int SYNC_INTERVAL_SECONDS = (int) TimeUnit.HOURS.toSeconds(SYNC_INTERVAL_HOURS);
    private static final int SYNC_FLEXTIME_SECONDS = SYNC_INTERVAL_SECONDS / 3;
   static void ScheduleFirebaseJobDispatcher(@NonNull Context context)
    {

        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);
        Job MoviesSyncJob=dispatcher.newJobBuilder().setService(MovieJobService.class).setTag("Movies-sync").setConstraints(Constraint.ON_ANY_NETWORK).setLifetime(Lifetime.FOREVER).setRecurring(true).setTrigger(Trigger.executionWindow(SYNC_INTERVAL_SECONDS,
                SYNC_INTERVAL_SECONDS + SYNC_FLEXTIME_SECONDS)).setReplaceCurrent(true).build();
        dispatcher.schedule(MoviesSyncJob);

    }
    public static void initialize(final Context context)
    {
        InsertDataInBackground.insertData(context);
        ScheduleFirebaseJobDispatcher(context);
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                Cursor cursor=context.getContentResolver().query(MoviesContract.MoviesEntry.FINAL_URI,null,null,null,null);
                if(cursor==null || cursor.getCount()==0)
                {
                    startImmediateSync(context);
                }
                cursor.close();
            }
        });
        thread.start();
    }
    public static void startImmediateSync(Context context)
    {
        Intent intent=new Intent(context,InsertDataService.class);
        context.startService(intent);
    }
}
