package com.example.bfinerocks.threadpractice;

import android.os.AsyncTask;

/**
 * Created by BFineRocks on 12/15/14.
 */
public class AsyncTaskThread extends AsyncTask<Void, Void, String>{
    private AsyncTaskThreadInterface mAsyncTaskThreadInterface;

    public AsyncTaskThread(AsyncTaskThreadInterface taskInterface){
        this.mAsyncTaskThreadInterface = taskInterface;
    }

    @Override
    protected String doInBackground(Void... voids) {
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mAsyncTaskThreadInterface.updatedText(s);
    }

    public interface AsyncTaskThreadInterface {
        public void updatedText(String stringOfText);
    }
}
