package com.example.bfinerocks.threadpractice;

import android.os.Handler;
import android.util.Log;

import java.util.concurrent.Executor;

/**
 * Created by BFineRocks on 12/15/14.
 */
public class ThreadExecutorThread implements Executor {
    private Handler mHandler;

    public ThreadExecutorThread(Handler handler){
        this.mHandler = handler;
    }
    @Override
    public void execute(Runnable runnable) {
        Thread thread = new Thread(runnable);
        String threadID = String.valueOf(thread.getId());
        thread.start();
        Log.i("ThreadExecutor", threadID);
    }

}
