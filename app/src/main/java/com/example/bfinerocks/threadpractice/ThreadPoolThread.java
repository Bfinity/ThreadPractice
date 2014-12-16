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
    private static int NUM_OF_INITIAL_THREADS = Runtime.getRuntime().availableProcessors();
    private static int NUM_OF_MAX_THREADS = Runtime.getRuntime().availableProcessors();
    private static final int KEEP_ALIVE_TIME = 1;
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
    private final BlockingQueue<Runnable> queue = new LinkedBlockingDeque<Runnable>();
    private ThreadPoolExecutor mThreadPoolExecutor;
    private ThreadPoolInterface mThreadPoolInterface;
    ThreadFactoryClass mThreadFactoryClass = new ThreadFactoryClass();

    private ThreadPoolThread(){
        mThreadPoolExecutor = new ThreadPoolExecutor(NUM_OF_INITIAL_THREADS, NUM_OF_MAX_THREADS,
                KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, queue, mThreadFactoryClass);
        Log.i("constructor", "Constructor called");
    }

    public static ThreadPoolThread getThreadPoolThread(ThreadPoolInterface threadPoolInterface){
        if(sThreadPoolThread == null){
            sThreadPoolThread = new ThreadPoolThread();
        }
        Log.i("threadpool", "Threadpool called");
        sThreadPoolThread.mThreadPoolInterface = threadPoolInterface;
        return sThreadPoolThread;
    }

    public void addNewTasksToQueue(Runnable runnable){
        sThreadPoolThread.mThreadPoolExecutor.execute(runnable);
        String threadID = String.valueOf(Thread.currentThread().getId());
        Log.i("threadPool", threadID + Thread.currentThread().getName());
        mThreadPoolInterface.updateData(threadID);
    }

    public interface ThreadPoolInterface{
        public void updateData(String data);
    }

    public class ThreadFactoryClass implements java.util.concurrent.ThreadFactory{
        @Override
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("ThreadFactory");
            thread.setPriority(Thread.NORM_PRIORITY);
            return thread;
        }
    }







}
