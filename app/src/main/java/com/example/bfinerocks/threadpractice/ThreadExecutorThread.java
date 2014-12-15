package com.example.bfinerocks.threadpractice;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
        Message msg = new Message();
        msg.setTarget(mHandler);
        Bundle bundle = new Bundle();
        bundle.putString("executor", threadID);
        msg.setData(bundle);
        msg.sendToTarget();
    }

}
