package com.example.bfinerocks.threadpractice;

/**
 * Created by BFineRocks on 12/15/14.
 */
public class ThreadPoolThread{

    public static ThreadPoolThread sThreadPoolThread;

    private ThreadPoolThread(){

    }

    public static ThreadPoolThread getThreadPoolThread(){
        if(sThreadPoolThread == null){
            sThreadPoolThread = new ThreadPoolThread();
        }
        return sThreadPoolThread;
    }


}
