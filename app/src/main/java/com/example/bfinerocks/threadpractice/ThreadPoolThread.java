package com.example.bfinerocks.threadpractice;

import android.util.Log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by BFineRocks on 12/15/14.
 */
public class ThreadPoolThread{

    public static ThreadPoolThread sThreadPoolThread;
    private static int NUM_OF_INITIAL_THREADS = 1;
    private static int NUM_OF_MAX_THREADS = 2;
    private static final int KEEP_ALIVE_TIME = 1;
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
    private final BlockingQueue<Runnable> queue = new LinkedBlockingDeque<Runnable>();
    private ThreadPoolExecutor mThreadPoolExecutor;

    private ThreadPoolThread(){
        mThreadPoolExecutor = new ThreadPoolExecutor(NUM_OF_INITIAL_THREADS, NUM_OF_MAX_THREADS,
                KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, queue);
    }

    public static ThreadPoolThread getThreadPoolThread(){
        if(sThreadPoolThread == null){
            sThreadPoolThread = new ThreadPoolThread();
        }
        return sThreadPoolThread;
    }

    public void addNewTasksToQueue(Runnable runnable){
        sThreadPoolThread.mThreadPoolExecutor.execute(runnable);
        String threadID = String.valueOf(Thread.currentThread().getId());
        Log.i("threadPool", threadID);
    }







}
