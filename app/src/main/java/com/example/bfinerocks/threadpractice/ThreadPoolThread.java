package com.example.bfinerocks.threadpractice;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * Created by BFineRocks on 12/15/14.
 */
public class ThreadPoolThread{

    public static ThreadPoolThread sThreadPoolThread;
    private static int NUM_OF_CORES = Runtime.getRuntime().availableProcessors();
    private static final int KEEP_ALIVE_TIME = 1;
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
    private final BlockingQueue<Runnable> queue;

    private ThreadPoolThread(){
        queue = new LinkedBlockingDeque<Runnable>();
    }

    public static ThreadPoolThread getThreadPoolThread(){
        if(sThreadPoolThread == null){
            sThreadPoolThread = new ThreadPoolThread();
        }
        return sThreadPoolThread;
    }




}
