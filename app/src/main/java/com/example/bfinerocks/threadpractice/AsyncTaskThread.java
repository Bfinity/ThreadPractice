package com.example.bfinerocks.threadpractice;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by BFineRocks on 12/15/14.
 */
public class AsyncTaskThread extends AsyncTask<Void, Void, Void>{
    @Override
    protected Void doInBackground(Void... voids) {
        String thread = String.valueOf(Thread.currentThread().getId());
        Log.i("AsyncTaskThread", thread);
        return null;
    }
}
