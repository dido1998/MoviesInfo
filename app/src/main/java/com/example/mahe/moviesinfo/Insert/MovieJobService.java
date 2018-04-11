package com.example.mahe.moviesinfo.Insert;

import android.os.AsyncTask;

import com.firebase.jobdispatcher.Job;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Job;

/**
 * Created by Mahe on 6/6/2017.
 */

public class MovieJobService extends JobService {
    AsyncTask<Void,Void,Void> task;


    @Override
    public boolean onStartJob(final JobParameters job) {
        task=new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                InsertDataInBackground.insertData(getApplicationContext());
                jobFinished(job,false);
                return null;
            }
            @Override
            protected void onPostExecute(Void avoid)
            {
                jobFinished(job,false);
            }
        };
        task.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        if(task!=null)
        {
            task.cancel(false);
        }

        return true;
    }
}
